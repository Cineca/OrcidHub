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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration implements EnvironmentAware {

    private static final String ENV_SPRING_MAIL = "mail.";
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final String PROP_HOST = "host";
    private static final String DEFAULT_PROP_HOST = "localhost";
    private static final String PROP_PORT = "port";
    private static final String PROP_USER = "username";
    private static final String PROP_PASSWORD = "password";
    private static final String PROP_PROTO = "protocol";
    private static final String PROP_TLS = "tls";
    private static final String PROP_AUTH = "auth";
    private static final String PROP_SMTP_AUTH = "mail.smtp.auth";
    private static final String PROP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    private final Logger log = LoggerFactory.getLogger(MailConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_SPRING_MAIL);
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        log.debug("Configuring mail server");
        String host = propertyResolver.getProperty(PROP_HOST, DEFAULT_PROP_HOST);
        int port = propertyResolver.getProperty(PROP_PORT, Integer.class, 0);
        String user = propertyResolver.getProperty(PROP_USER);
        String password = propertyResolver.getProperty(PROP_PASSWORD);
        String protocol = propertyResolver.getProperty(PROP_PROTO);
        Boolean tls = propertyResolver.getProperty(PROP_TLS, Boolean.class, false);
        Boolean auth = propertyResolver.getProperty(PROP_AUTH, Boolean.class, false);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (host != null && !host.isEmpty()) {
            sender.setHost(host);
        } else {
            log.warn("Warning! Your SMTP server is not configured. We will try to use one on localhost.");
            log.debug("Did you configure your SMTP settings in your application.yml?");
            sender.setHost(DEFAULT_HOST);
        }
        sender.setPort(port);
        sender.setUsername(user);
        sender.setPassword(password);

        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, auth.toString());
        sendProperties.setProperty(PROP_STARTTLS, tls.toString());
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, protocol);
        sender.setJavaMailProperties(sendProperties);
        return sender;
    }
}
