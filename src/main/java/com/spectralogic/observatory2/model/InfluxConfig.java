//===================================================================
// InfluxConfig.java
//      Description:
//          This class models the configuration parameters for the
//          influx database.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.model;

public class InfluxConfig {
    private String databaseName;
    private String username;
    private String password;
    private String url;

    //===========================================
    // Getters
    //===========================================
    public String getUrl() { return url; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }
    public String getDatabaseName() { return databaseName; }
    
    //===========================================
    // Setters
    //===========================================
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setUrl(String url) { this.url = url; }
}
