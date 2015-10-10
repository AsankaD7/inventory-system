/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * copyright to IJSE
 *
 * @author niroth
 */
public class IdController {

    public static String getLastId(String tableName, String columnName) throws ClassNotFoundException, SQLException {
        String query = "select "+ columnName + " from " + tableName + " order by " + columnName + " desc limit 1";
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(query);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
