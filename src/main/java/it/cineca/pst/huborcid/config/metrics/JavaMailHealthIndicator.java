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
package it.cineca.pst.huborcid.config.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;

import javax.mail.MessagingException;

/**
 * SpringBoot Actuator HealthIndicator check for JavaMail.
 */
public class JavaMailHealthIndicator extends AbstractHealthIndicator {

    private final Logger log = LoggerFactory.getLogger(JavaMailHealthIndicator.class);

    private JavaMailSenderImpl javaMailSender;

    public JavaMailHealthIndicator(JavaMailSenderImpl javaMailSender) {
        Assert.notNull(javaMailSender, "javaMailSender must not be null");
        this.javaMailSender = javaMailSender;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        log.debug("Initializing JavaMail health indicator");
        try {
            javaMailSender.getSession().getTransport().connect(javaMailSender.getHost(),
                    javaMailSender.getPort(),
                    javaMailSender.getUsername(),
                    javaMailSender.getPassword());

            builder.up();

        } catch (MessagingException e) {
            log.debug("Cannot connect to e-mail server. Error: {}", e.getMessage());
            builder.down(e);
        }
    }
}
