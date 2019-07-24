package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.FlashSaleProduct;
import com.tinlm.snef.model.Order;
import com.tinlm.snef.model.Store;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean createOrder(String confirmationCode, int accountId) {
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
                stm.setInt(5, accountId);
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


    public Order getLastOrder() throws SQLException, ClassNotFoundException {
        Order result = new Order();
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select OrderID, DateOrder, ConfirmationCode, Status, RatingPoint, CustomerCustomerId  " +
                        "from snef_part2.Order where concat(OrderID) in (select concat(max(orderID)) " +
                        "from snef_part2.Order)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    result.setOrderId(rs.getInt("OrderId"));
                    result.setDateOrder(rs.getDate("DateOrder"));
                    result.setConfirmationCode(rs.getString("ConfirmationCode"));
                    result.setStatus(rs.getBoolean("Status"));
                    result.setRatingPoint(rs.getFloat("RatingPoint"));
                    result.setAccountId(rs.getInt("CustomerCustomerId"));
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return result;
    }


}
