//===================================================================
// CreateToken.java
//      Description:
//          Creates API tokens for authentication.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.command;

import com.spectralogic.observatory2.server.model.User;
import com.spectralogic.observatory2.server.util.sqlite.SQLiteConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateToken {
    private static final Logger log = LoggerFactory.getLogger(CreateToken.class);

    public static User forUser(String token, String site, SQLiteConnector sqlite) throws Exception {
        log.info("Creating API token from provided token.");

        User user = sqlite.insertToken(token, site);

        log.info("Successfully created token.");

        return user;
    }
}
