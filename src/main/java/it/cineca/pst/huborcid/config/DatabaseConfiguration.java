/**
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.cineca.pst.huborcid.config;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import java.util.Arrays;

@Configuration
@EnableJpaRepositories("it.cineca.pst.huborcid.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    private RelaxedPropertyResolver liquiBasePropertyResolver;

    private Environment env;

    @Autowired(required = false)
    private MetricRegistry metricRegistry;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        this.liquiBasePropertyResolver = new RelaxedPropertyResolver(env, "liquiBase.");
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingClass(name = "it.cineca.pst.huborcid.config.HerokuDatabaseConfiguration")
    @Profile("!" + Constants.SPRING_PROFILE_CLOUD)
    public DataSource dataSource() {
        log.debug("Configuring Datasource");
        if (dataSourcePropertyResolver.getProperty("url") == null && dataSourcePropertyResolver.getProperty("databaseName") == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                    " cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        if(StringUtils.isEmpty(dataSourcePropertyResolver.getProperty("jndi"))){
	        config.setDataSourceClassName(dataSourcePropertyResolver.getProperty("dataSourceClassName"));
	        if(StringUtils.isEmpty(dataSourcePropertyResolver.getProperty("url"))) {
	            config.addDataSourceProperty("databaseName", dataSourcePropertyResolver.getProperty("databaseName"));
	            config.addDataSourceProperty("serverName", dataSourcePropertyResolver.getProperty("serverName"));
	        } else {
	            config.addDataSourceProperty("url", dataSourcePropertyResolver.getProperty("url"));
	        }
	        config.addDataSourceProperty("user", dataSourcePropertyResolver.getProperty("username"));
	        config.addDataSourceProperty("password", dataSourcePropertyResolver.getProperty("password"));
	        config.setConnectionTimeout(10000);
	        config.setMaximumPoolSize(25);
        }else{
        	final JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            dataSourceLookup.setResourceRef(true);
        	DataSource dataSourceTemp = null;
            try {
                dataSourceTemp = dataSourceLookup.getDataSource(dataSourcePropertyResolver.getProperty("jndi"));
            } catch (DataSourceLookupFailureException e) {
                log.error("DataSource not found.");
            }
            config.setDataSource(dataSourceTemp);
        }

        if (metricRegistry != null) {
            config.setMetricRegistry(metricRegistry);
        }
        return new HikariDataSource(config);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts(liquiBasePropertyResolver.getProperty("context"));
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_FAST)) {
            if ("org.h2.jdbcx.JdbcDataSource".equals(dataSourcePropertyResolver.getProperty("dataSourceClassName"))) {
                liquibase.setShouldRun(true);
                log.warn("Using '{}' profile with H2 database in memory is not optimal, you should consider switching to" +
                    " MySQL or Postgresql to avoid rebuilding your database upon each start.", Constants.SPRING_PROFILE_FAST);
            } else {
                liquibase.setShouldRun(false);
            }
        } else {
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }
}
