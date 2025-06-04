//===================================================================
// ServerConfig.java
//      Description:
//          This class models the server configuration
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.model;

public class ServerConfig {
    private InfluxConfig influxdb;
    private SqliteConfig sqlite;
    private String certPath;

    //===========================================
    // Getters
    //===========================================
    public InfluxConfig getInfluxdb() { return influxdb; }
    public SqliteConfig getSqlite() { return sqlite; }
    public String getCertPath() { return certPath; }

    //===========================================
    // Setters
    //===========================================
    public void setInfluxdb(InfluxConfig influx) { this.influxdb = influx; }
    public void setSqlite(SqliteConfig sqlite) { this.sqlite = sqlite; }
    public void setCertPath(String path) { this.certPath = path; }
}

