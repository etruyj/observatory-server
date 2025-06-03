//===================================================================
// BlackPearlApi.java
//      Description:
//          The API calls related to the BlackPearl calls.
//===================================================================

package com.spectralogic.observatory2.server.api;

import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("/bp")
public class BlackPearlApi {
    @Context
    Configuration config;

    @POST
    @Path("/messages")
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(@HeaderParam("Authorization") String token, String body) {
        return BlackPearlImp.storeMessages(token, body, config);
    }
}
