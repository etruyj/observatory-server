//===================================================================
// ValidateToken.java
//      Description:
//          Verify the token exists in the database by returning a 
//          User object.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.command;

import com.spectralogic.observatory2.server.model.User;
import com.spectralogic.observatory2.server.util.sqlite.SQLiteConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateToken {
    private static final Logger log = LoggerFactory.getLogger(ValidateToken.class);

    public static User forUser(String token, SQLiteConnector sqlite) throws Exception {
        log.info("Searching for user associated with token.");

        User user = sqlite.selectToken(token);

        log.info("Token belongs to site [" + user.getSite() + "]");

        return user;
    }
}
