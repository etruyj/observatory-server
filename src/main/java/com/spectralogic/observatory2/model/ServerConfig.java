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

    //===========================================
    // Getters
    //===========================================
    public InfluxConfig getInfluxdb() { return influxdb; }

    //===========================================
    // Setters
    //===========================================
    public void setInfluxdb(InfluxConfig influx) { this.influxdb = influx; }
}

