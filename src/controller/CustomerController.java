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
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author linux
 */
public class CustomerController {

    public static int addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("Insert into Customer value(?,?,?,?)");
        prepareStatement.setObject(1, customer.getId());
        prepareStatement.setObject(2, customer.getName());
        prepareStatement.setObject(3, customer.getAddress());
        prepareStatement.setObject(4, customer.getSalary());
        int res = prepareStatement.executeUpdate();
        return res;
    }

    public static int updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("Update Customer set name=?,address=?,salary=? where id=?");
        prepareStatement.setObject(4, customer.getId());
        prepareStatement.setObject(1, customer.getName());
        prepareStatement.setObject(2, customer.getAddress());
        prepareStatement.setObject(3, customer.getSalary());
        int res = prepareStatement.executeUpdate();
        return res;
    }

    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Customer where id='" + id + "'");
        if (rst.next()) {
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
            return customer;
        } else {
            return null;
        }
    }

    public static int deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        return stm.executeUpdate("Delete From Customer where id='" + id + "'");
    }
    public  static ArrayList<Customer>getAllCustomer() throws ClassNotFoundException, SQLException{
        DBConnection ob=DBConnection.getDBConnection();
        Connection connection=ob.getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery("select * From Customer");
        ArrayList<Customer>customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
            customerList.add(customer);
        }
        return customerList;
    }
}
