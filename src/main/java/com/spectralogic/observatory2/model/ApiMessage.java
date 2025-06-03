//===================================================================
// ApiMessage.java
//  Description:
//      The container class for messages returned by the API.
//
// Created by etruyj
//===================================================================

package com.spectralogic.observatory2.server.model;

public class ApiMessage {
    String message;

    public ApiMessage() {}
    public ApiMessage(String message) { this.message = message; }
    public ApiMessage(int status_code) {
        switch(status_code) {
            case 200:
                message = "Success";
                break;
            case 401:
                message = "Invalid credentials";
                break;
            case 403:
                message = "User is not authorized to perform the requested task";
                break;
            case 404:
                message = "Resource does not exist";
                break;
            case 405:
                message = "User has exceeded maxium limit for resource";
            case 409:
                message = "Resource already exists";
                break;
            case 422:
                message = "Request cannot be processed in the current state.";
                break;
        }
    }

    //===========================================
    // Getters
    //===========================================
    public String getMessage() { return message; }
    
    //===========================================
    // Setters
    //===========================================

    public void setMessage(String message) { this.message = message; }
}
