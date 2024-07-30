package com.belajar.maven;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
    public static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/test");
        config.setUsername("postgres");
        config.setPassword("my1stpsql");

        config.setMaxLifetime(10 * 60_000);
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(60_000);
        config.setMinimumIdle(3);

        dataSource = new HikariDataSource(config);

    }


    public HikariDataSource getDataSource (){
        return dataSource;
    }
}
