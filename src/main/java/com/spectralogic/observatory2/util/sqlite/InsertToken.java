//===================================================================
// InsertToken.java
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

public class InsertToken {
    public static User fromFields(String token, String site, Connection conn) throws SQLException, Exception {
        String query = "INSERT INTO TOKEN (id, site) VALUES (?,?)";
    
        PreparedStatement pst = conn.prepareStatement(query);

        pst.setString(1, token);
        pst.setString(2, site);

        pst.execute();

        return SelectToken.withToken(token, conn);
    }
}
