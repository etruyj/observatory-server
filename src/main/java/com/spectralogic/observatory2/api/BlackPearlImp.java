//===================================================================
// BlackPearlImp.java
//      Description:
//          The implementation of the blackpearl api functions.
//===================================================================

package com.spectralogic.observatory2.server.api;;

import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Response;

import com.spectralogic.blackpearl.nacre.model.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.spectralogic.observatory2.server.command.ObServerController;
import com.spectralogic.observatory2.server.model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlackPearlImp {
    private static final Logger log = LoggerFactory.getLogger(BlackPearlImp.class);
    private static final Gson gson = new GsonBuilder()
                                        .setPrettyPrinting()
                                        .create();

    public static Response storeMessages(String token, String body, Configuration config) {
        ObServerController conn = (ObServerController) config.getProperty("controller");

        List<Message> messages = gson.fromJson(body, new TypeToken<List<Message>>() {}.getType());

        log.info("Servng API call to store (" + messages.size() + ") BlackPearl messages");

        try {
            User user = AuthImp.authenticateToken(token, conn);        
            
            if(user != null) {
                conn.storeBlackPearlMessages(user, messages);

                log.info("Successfully serviced the api call to store BlackPearl messages.");

                return Response.status(200).build();
            }
            else {
                throw new Exception("[INVALID CREDENTIALS] Invalid token.");
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            log.error("Failed to store messages");
            e.printStackTrace();

            return ExceptionParser.buildResponse(e);
        }
    }
}
