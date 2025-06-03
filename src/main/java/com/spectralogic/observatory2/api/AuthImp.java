//===================================================================
// AuthImp.java
//      Description:
//          Authentication implementation functions.
//===================================================================

package com.spectralogic.observatory2.server.api;

import jakarta.ws.rs.core.Response;

import com.spectralogic.observatory2.server.model.User;
import com.spectralogic.observatory2.server.command.ObServerController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthImp {
    private static final Logger log = LoggerFactory.getLogger(AuthImp.class);

    public static User authenticateToken(String token, ObServerController conn) {
        System.err.println("TOKEN: " + token);
        log.debug("Verifying if token is valid.");

        User user = null;

        try {    
            // Bearer Auth Only ATM

            //conn.deactivateStaleTokens(); // Clear out the old before verifying
            
            if(token.length() > "Bearer Token".length()) {
                user = conn.validateToken(token.substring("Bearer ".length(), token.length()));

                return user;
            }
            else {
                log.warn("Token failed validation test. Processing aborted.");
                return null;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
