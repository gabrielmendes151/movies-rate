package com.texoit.challenge.movies_reate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatabaseConfig {

    private static final String POSTGRES_IMAGE = "postgres:15-alpine";
    private static final String DATABASE_NAME = "movies_rate";
    private static final String USERNAME = "test_user";
    private static final String PASSWORD = "test_password";

    @Bean(destroyMethod = "stop")
    public PostgreSQLContainer<?> postgresqlContainer() {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            DockerImageName.parse(POSTGRES_IMAGE)
                .asCompatibleSubstituteFor("postgres")
        )
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withReuse(true); // Reutiliza container entre execuções

        container.start();

        log.info("PostgreSQL container started: {}", container.getJdbcUrl());
        return container;
    }

    @Bean
    public DataSource dataSource(PostgreSQLContainer<?> postgresqlContainer) {
        return DataSourceBuilder.create()
            .url(postgresqlContainer.getJdbcUrl())
            .username(postgresqlContainer.getUsername())
            .password(postgresqlContainer.getPassword())
            .driverClassName("org.postgresql.Driver")
            .build();
    }
}