//===================================================================
// SelectToken.java
//      Description:
//          This class handles inserting tuples into the token table.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.util.sqlite;

import com.spectralogic.observatory2.server.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectToken {
    private static final String query_str = "SELECT token.id, token.site FROM token ";

    public static User withToken(String token, Connection conn) throws Exception, SQLException {
        String query = query_str + "WHERE id=?";

        System.out.println("Token " + token);
        PreparedStatement pst = conn.prepareStatement(query);

        pst.setString(1, token);

        ResultSet results = pst.executeQuery();

        if(results.next()) {
            return populateFields(results);
        } else {
            throw new Exception("[NOT FOUND] Invalid token");
        }
    }

    //===========================================
    // Private Functions
    //===========================================
    private static User populateFields(ResultSet result) throws SQLException {
        User user = new User();

        user.setSite(result.getString("site"));

        return user;
    }
}
