package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    public int getQuantityByFSPId(int flashsaleProductId) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select SUM(Quantity) as TotalQuantity from OrderDetail where FlashSaleProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, flashsaleProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = rs.getInt("TotalQuantity");
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm, con);
        }
        return result;
    }

    public boolean createOrderDetail(int orderId, int fspId, int quantity, float orderDetailPrice) {
        boolean result = false;

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "insert into `OrderDetail`(`OrderOrderId`, `FlashSaleProductId`, `Quantity`, `OrderDetailPrice`)" +
                        " values(?,?,?,?)";
                stm =con.prepareStatement(sql);

                stm.setInt(1, orderId);
                stm.setInt(2, fspId);
                stm.setInt(3, quantity);
                stm.setFloat(4, orderDetailPrice);
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

    public List<OrderDetail> getAllOrderDetailByOrderId(int orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetail> result = new ArrayList<>();
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select OrderDetailId, OrderOrderId, FlashSaleProductId, Quantity, OrderDetailPrice " +
                        "from snef_part2.OrderDetail where OrderOrderId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int orderDetailId = rs.getInt("OrderDetailId");
                    int flashSaleProductId = rs.getInt("FlashSaleProductId");
                    int quantity = rs.getInt("Quantity");
                    float orderDetailPrice = rs.getInt("OrderDetailPrice");

                    result.add(new OrderDetail(orderDetailId, orderId, flashSaleProductId, quantity, orderDetailPrice));
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return result;
    }
}
