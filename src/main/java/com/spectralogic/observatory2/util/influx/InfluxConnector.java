//===================================================================
// InfluxConnector.java
//      Description:
//          This class acts as an interface for all the influx commands
//          passed through the system.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.util.influx;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class InfluxConnector {
    private InfluxDB influx;

    public InfluxConnector(String database_url, String username, String password, String database_name) {
        influx = InfluxDBFactory.connect(database_url, username, password);
        influx.setDatabase(database_name);
    }

    //===========================================
    // Database Calls
    //===========================================
    // Write messages to the BlackPearl. Since the model is vaguely named Message, specifically reference the
    // model in the method parameter instead of during import to avoid clashes.
    public void writeBlackPearlMessages(String host, List<com.spectralogic.blackpearl.nacre.model.Message> messages) throws Exception {
        WriteBlackPearlMessages.fromList(host, messages, influx);
    }
}
