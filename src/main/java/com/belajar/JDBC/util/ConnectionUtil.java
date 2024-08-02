package com.belajar.JDBC.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.util.Properties;

public class ConnectionUtil {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
            }
            // Load a properties file from class path, inside static block
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driverClassName"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));

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
