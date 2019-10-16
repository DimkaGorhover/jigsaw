package org.gd.jigsaw;

import org.gd.jigsaw.service.H2UserService;
import org.gd.jigsaw.service.LoggedUserService;
import org.gd.jigsaw.service.TimedUserService;
import org.gd.jigsaw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @since 2019-10-15
 */
@EnableWebFlux
@SpringBootApplication(scanBasePackageClasses = App.class)
public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    @Autowired
    UserService userService(JdbcTemplate jdbcTemplate) {

        jdbcTemplate.execute("CREATE TABLE users(id SERIAL, name VARCHAR(255))");

        UserService userService = H2UserService.builder()
                .jdbcTemplate(jdbcTemplate)
                .build();

        if (LoggerFactory.getLogger(LoggedUserService.class).isDebugEnabled())
            userService = LoggedUserService.builder()
                    .userService(userService)
                    .build();

        userService = TimedUserService.builder()
                .userService(userService)
                .build();

        userService.add(() -> "admin");

        if (LOG.isInfoEnabled())
            LOG.info("[userService]: {}", userService);

        return userService;
    }
}
