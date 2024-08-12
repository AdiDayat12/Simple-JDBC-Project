package com.belajar.JDBC;

import com.belajar.JDBC.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnection {
    private Connection connection;

    protected void getConnection () throws SQLException {
        connection = ConnectionUtil.dataSource.getConnection();
    }

    void tearDown () throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
