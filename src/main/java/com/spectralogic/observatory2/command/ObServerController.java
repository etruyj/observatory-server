//===================================================================
// ObServerController.java
//      Description:
//          This class is the interface between any UI or parent scripts
//          and the underlying commands.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.command;

import com.spectralogic.observatory2.server.model.User;
import com.spectralogic.observatory2.server.util.influx.InfluxConnector;
import com.spectralogic.observatory2.server.util.sqlite.SQLiteConnector;

import java.util.List;

public class ObServerController {
    private static InfluxConnector influx;
    private static SQLiteConnector sqlite;

    public ObServerController(String influxUrl, String influxdb, String user, String pass, String sqlitePath) throws Exception {
        influx = new InfluxConnector(influxUrl, user, pass, influxdb);
        sqlite = new SQLiteConnector(sqlitePath);
        sqlite.initializeDatabase();
    }


    //============================================
    // Commands
    //============================================
    public User createToken(String token, String site) throws Exception {
        return CreateToken.forUser(token, site, sqlite);
    }

    public void storeBlackPearlMessages(User user, List<com.spectralogic.blackpearl.nacre.model.Message> messages) throws Exception {
        StoreBlackPearlMessages.fromList(user, messages, influx);
    }

    public User validateToken(String token) throws Exception {
        return ValidateToken.forUser(token, sqlite);
    }
}
