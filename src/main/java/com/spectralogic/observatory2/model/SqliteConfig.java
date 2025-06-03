//===================================================================
// SQLiteConfig.java
//      Description:
//          This class holds the configuration information for the
//          sqlite database.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.model;

public class SqliteConfig {
    private String database;

    //===========================================
    // Getters
    //===========================================
    public String getDatabase() { return database; }

    //===========================================
    // Setters
    //===========================================
    public void setDatabase(String db) { this.database = db; }
}
