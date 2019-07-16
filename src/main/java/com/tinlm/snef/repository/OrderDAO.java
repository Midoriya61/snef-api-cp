package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderDAO {

    public boolean createOrder(String confirmationCode, int customerId) {
        boolean result = false;

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "insert into `Order`(`DateOrder`, `ConfirmationCode`, `Status`, `RatingPoint`, `CustomerCustomerId`)" +
                        " values(?,?,?,?,?)";
                stm =con.prepareStatement(sql);
                LocalDate localDate = LocalDate.now();
                stm.setString(1, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
                stm.setString(2, confirmationCode);
                stm.setInt(3, 0);
                stm.setFloat(4, 0);
                stm.setInt(5, customerId);
                int row = stm.executeUpdate();
                if (row > 0){
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs,stm,con);
        }

        return  result;
    }

}
