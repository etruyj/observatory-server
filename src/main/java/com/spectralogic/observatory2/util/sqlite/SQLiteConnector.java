//===================================================================
// SQLiteConnector.java
//      Description:
//          This class interfaces the different database commands for
//          SQLite.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.util.sqlite;

import com.spectralogic.observatory2.server.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnector {
    private Connection conn;

    public SQLiteConnector(String database_path) throws Exception {
        conn = null;
        String url = "jdbc:sqlite:" + database_path;

        conn = DriverManager.getConnection(url);
    }

    //===========================================
    // Database Calls
    //===========================================
    public void initializeDatabase() throws SQLException {
        CreateTable.tokenTable(conn);
    }

    public User insertToken(String token, String site) throws Exception, SQLException {
        return InsertToken.fromFields(token, site, conn);
    }

    public User selectToken(String token) throws Exception, SQLException {
        return SelectToken.withToken(token, conn);
    }
}
