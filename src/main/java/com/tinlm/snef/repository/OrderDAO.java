package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.FlashSaleProduct;
import com.tinlm.snef.model.Order;
import com.tinlm.snef.model.OrderDetail;
import com.tinlm.snef.model.Store;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null){
            rs.close();
        }
        if (stm !=null){
            stm.close();
        }
        if (con!=null){
            con.close();
        }
    }

    public boolean createOrder(String confirmationCode, int accountId) {
        boolean result = false;

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null) {
                String sql = "insert into `Order`(`DateOrder`, `ConfirmationCode`, `Status`, `RatingPoint`, `CustomerCustomerId`)" +
                        " values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                LocalDate localDate = LocalDate.now();
                stm.setString(1, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
                stm.setString(2, confirmationCode);
                stm.setInt(3, 0);
                stm.setFloat(4, 0);
                stm.setInt(5, accountId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm, con);
        }

        return result;
    }

    public List<Order> getAllOrder() {
        List<Order> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null) {

                String sql = "select  o.OrderId, o.DateOrder, o.ConfirmationCode, o.Status, o.RatingPoint, o.CustomerCustomerId, " +
                        "s.StoreId, s.StoreName " +
                        "from snef_part2.Order o, OrderDetail od, FlashsaleProduct fsp, StoreProduct sp, Store s " +
                        "where od.OrderOrderId = o.OrderId " +
                        "and od.FlashSaleProductId = fsp.FlashSaleProductId and " +
                        "fsp.StoreProductId = sp.StoreProductId and sp.StoreId = s.StoreId group by OrderId";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {

                    int orderId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as OrderQuantity, OrderOrderId from OrderDetail" +
                            " Where OrderOrderId = ? group by snef_part2.OrderDetail.OrderOrderId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1, orderId);
                    ResultSet rsOrderQuantity = stm.executeQuery();
                    int orderQuantity = 0;
                    if (rsOrderQuantity.next()) {
                        orderQuantity = rsOrderQuantity.getInt("OrderQuantity");

                    }
                    rsOrderQuantity.close();

                    String sqlPrice = "select SUM(OrderDetailPrice) as ToTalPrice, OrderOrderId from OrderDetail" +
                            " Where OrderOrderId = ? group by snef_part2.OrderDetail.OrderOrderId";
                    stm = con.prepareStatement(sqlPrice);
                    stm.setInt(1, orderId);
                    ResultSet rsTotalPrice = stm.executeQuery();
                    int totalPrice = 0;
                    if (rsTotalPrice.next()) {
                        totalPrice = rsTotalPrice.getInt("TotalPrice");
                    }
                    rsTotalPrice.close();

                    Order order = new Order();

                    order.setOrderId(orderId);
                    order.setDateOrder(rs.getDate(2));
                    order.setTotalPrice(totalPrice);
                    order.setConfirmationCode(rs.getString(3));
                    order.setOrderQuantity(orderQuantity);
                    order.setAccountId(rs.getInt(6));
                    order.setStatus(rs.getBoolean(4));
                    order.setRatingPoint(rs.getFloat(5));
                    order.setStoreId(rs.getInt(7));
                    order.setStoreName(rs.getString(8));

                    result.add(order);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm, con);
        }
        return result;
    }

    public Order getLastOrder() throws SQLException, ClassNotFoundException {
        Order result = new Order();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null) {
                String sql = "select OrderID, DateOrder, ConfirmationCode, Status, RatingPoint, CustomerCustomerId  " +
                        "from snef_part2.Order where concat(OrderID) in (select concat(max(orderID)) " +
                        "from snef_part2.Order)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    result.setOrderId(rs.getInt("OrderId"));
                    result.setDateOrder(rs.getDate("DateOrder"));
                    result.setConfirmationCode(rs.getString("ConfirmationCode"));
                    result.setStatus(rs.getBoolean("Status"));
                    result.setRatingPoint(rs.getFloat("RatingPoint"));
                    result.setAccountId(rs.getInt("CustomerCustomerId"));
                }
            }
        } finally {
            MyConnection.closeConnection(rs, stm, con);
        }
        return result;
    }

    public Order getOrderById(int orderId) throws SQLException, ClassNotFoundException {
        Order result = new Order();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null) {
//                String sql2 = "select OrderID, DateOrder, ConfirmationCode, Status, RatingPoint, CustomerCustomerId" +
//                        " from snef_part2.Order where OrderID = ?";

                String sql = "select  o.OrderId, o.DateOrder, o.ConfirmationCode, o.Status, o.RatingPoint, o.CustomerCustomerId, " +
                        "s.StoreId, s.StoreName " +
                        "from snef_part2.Order o, OrderDetail od, FlashsaleProduct fsp, StoreProduct sp, Store s " +
                        "where o.OrderId = ? and o.OrderId = od.OrderOrderId " +
                        "and od.FlashSaleProductId = fsp.FlashSaleProductId and " +
                        "fsp.StoreProductId = sp.StoreProductId and sp.StoreId = s.StoreId group by OrderId";

                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                rs = stm.executeQuery();
                while (rs.next()) {

                    orderId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as OrderQuantity, OrderOrderId from OrderDetail" +
                            " Where OrderOrderId = ? group by snef_part2.OrderDetail.OrderOrderId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1, orderId);
                    ResultSet rsOrderQuantity = stm.executeQuery();
                    int orderQuantity = 0;
                    if (rsOrderQuantity.next()) {
                        orderQuantity = rsOrderQuantity.getInt("OrderQuantity");

                    }
                    rsOrderQuantity.close();

                    String sqlPrice = "select SUM(OrderDetailPrice) as ToTalPrice, OrderOrderId from OrderDetail" +
                            " Where OrderOrderId = ? group by snef_part2.OrderDetail.OrderOrderId";
                    stm = con.prepareStatement(sqlPrice);
                    stm.setInt(1, orderId);
                    ResultSet rsTotalPrice = stm.executeQuery();
                    int totalPrice = 0;
                    if (rsTotalPrice.next()) {
                        totalPrice = rsTotalPrice.getInt("TotalPrice");
                    }
                    rsTotalPrice.close();

                    result.setOrderId(orderId);
                    result.setDateOrder(rs.getDate(2));
                    result.setTotalPrice(totalPrice);
                    result.setConfirmationCode(rs.getString(3));
                    result.setOrderQuantity(orderQuantity);
                    result.setAccountId(rs.getInt(6));
                    result.setStatus(rs.getBoolean(4));
                    result.setRatingPoint(rs.getFloat(5));
                    result.setStoreId(rs.getInt(7));
                    result.setStoreName(rs.getString(8));

//
//                    result.setOrderId(rs.getInt("OrderId"));
//                    result.setDateOrder(rs.getDate("DateOrder"));
//                    result.setConfirmationCode(rs.getString("ConfirmationCode"));
//                    result.setStatus(rs.getBoolean("Status"));
//                    result.setRatingPoint(rs.getFloat("RatingPoint"));
//                    result.setAccountId(rs.getInt("CustomerCustomerId"));
                }
            }
        } finally {
            MyConnection.closeConnection(rs, stm, con);
        }
        return result;
    }

    public boolean updateRatingBar(int orderId, float ratingPoint) throws SQLException, ClassNotFoundException{

        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "UPDATE snef_part2.Order SET RatingPoint = ? WHERE OrderId = ?";

                stm = con.prepareStatement(sql);

                stm.setFloat(1, ratingPoint);
                stm.setInt(2, orderId);

                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }finally {
            closeConnection();
        }
        return false;
    }

}
