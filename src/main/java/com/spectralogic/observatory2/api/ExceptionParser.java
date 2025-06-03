//===================================================================
// ExceptionParser.java
//      Description:
//          This class parses the exceptions passed up to the script
//          and turns them into an API response.
//
// Created by etruyj
//===================================================================

package com.spectralogic.observatory2.server.api;

import com.google.gson.Gson;

import com.spectralogic.observatory2.server.model.ApiMessage;

import jakarta.ws.rs.core.Response;

public class ExceptionParser {
    public static Response buildResponse(Exception e) {
        Gson gson = new Gson();

        if(e.getMessage().contains("[INVALID CREDENTIALS]")) {
            return Response.status(401).entity(gson.toJson(new ApiMessage(401))).build();
        } else if(e.getMessage().contains("[PERMISSIONS]")) {
            return Response.status(403).entity(gson.toJson(new ApiMessage(403))).build();
        } else if(e.getMessage().contains("[DOES NOT EXIST]")) {
            return Response.status(403).entity(gson.toJson(new ApiMessage(403))).build();
        } else if(e.getMessage().contains("[NOT AUTHORIZED]")) {
            return Response.status(405).entity(gson.toJson(new ApiMessage(405))).build();
        } else if(e.getMessage().contains("[ALREADY EXISTS]")) {
            return Response.status(409).entity(gson.toJson(new ApiMessage(409))).build();
        } else if(e.getMessage().contains("[UNPROCESSABLE]")) {
            return Response.status(422).entity(gson.toJson(new ApiMessage(422))).build();
        } else {
            return Response.status(400).build();
        }
    }
}
