//===================================================================
// CreateTable.java
//      Description:
//          This class handles the initialization of the SQLite Database
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.util.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTable {
    private static final Logger log = LoggerFactory.getLogger(CreateTable.class);

    public static void tokenTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS token ("
                            + "id TEXT NOT NULL UNIQUE, "
                            + "site TEXT NOT NULL UNIQUE"
                            + ")";

        log.debug("Query: " + query);

        Statement stmt = conn.createStatement();

        stmt.execute(query);
    }
}
