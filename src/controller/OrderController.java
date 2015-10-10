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
import model.Order;
import model.OrderDetail;

/**
 * copyright to IJSE
 *
 * @author niroth
 */
public class OrderController {

    public static boolean addOrder(Order order, ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
        String query = "insert into Orders values(?,?,?)";
        Connection connection = DBConnection.getDBConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, order.getOrderId());
            prepareStatement.setObject(2, order.getDate());
            prepareStatement.setObject(3, order.getCustomerId());
            int res = prepareStatement.executeUpdate();
            if (res > 0) {
                boolean addOrderDetail = OrderDetailController.addOrderDetail(orderDetailList);
                if (!addOrderDetail) {
                    connection.rollback();
                }
                return addOrderDetail;
            }
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
