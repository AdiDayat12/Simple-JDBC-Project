package com.belajar.JDBC;

import com.belajar.JDBC.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionTest {

    @Test

    void testConn () throws SQLException {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.getDataSource().getConnection();
    }
}
