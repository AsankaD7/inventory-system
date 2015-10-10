/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Item;
import model.OrderDetail;

/**
 *
 * @author linux
 */
public class ItemController {

    public static Item searchItem(String code) throws ClassNotFoundException, SQLException {
        String query = "select * from Item where code = ?";
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setObject(1, code);
        ResultSet rst = prepareStatement.executeQuery();
        if (rst.next()) {
            Item item = new Item(rst.getString("code"), rst.getString("description"), rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
            return item;
        }
        return null;
    }

    public static boolean updateItemQty(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
        int res = 0;
        Connection connection = DBConnection.getDBConnection().getConnection();
        for (OrderDetail orderDetail : orderDetailList) {
            String query = "update Item set qtyOnHand = qtyOnHand - ? where code = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, orderDetail.getQty());
            prepareStatement.setObject(2, orderDetail.getItemCode());
            res += prepareStatement.executeUpdate();
        }
        if (res == orderDetailList.size()) {
            connection.commit();
            return true;
        }
        return false;
    }
    
    public static ArrayList<Item> getAllItems() throws ClassNotFoundException, SQLException {
        String query = "select * from Item";
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet rst = prepareStatement.executeQuery();
        ArrayList<Item> itemList = new ArrayList<>();
        while (rst.next()) {
            itemList.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return itemList;
    }
}
