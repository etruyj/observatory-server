//===================================================================
// StoreBlackPearlMessages.java
//      Description:
//          This class handles the command to store BlackPearl messages
//          in the InfluxDB.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.command;

import com.spectralogic.blackpearl.nacre.model.Message;
import com.spectralogic.observatory2.server.model.User;
import com.spectralogic.observatory2.server.util.influx.InfluxConnector;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreBlackPearlMessages {
    private static final Logger log = LoggerFactory.getLogger(StoreBlackPearlMessages.class);

    public static void fromList(User principal, List<Message> messages, InfluxConnector influx) throws Exception {
        log.info("Archiving (" + messages.size() + ") messages from host [" + principal.getSite() + "]");

        influx.writeBlackPearlMessages(principal.getSite(), messages);

        log.info("Archive successful.");
    }
}
