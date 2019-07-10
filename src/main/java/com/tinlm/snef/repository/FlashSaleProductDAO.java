package com.tinlm.snef.repository;

import com.tinlm.snef.SnefApplication;
import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.FlashSaleProduct;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getTopFlashSaleProduct
// 6/23/2019 TinLM Create getAllFSP

public class FlashSaleProductDAO {



    // 6/17/2019 TinLM Create
    // Get top 10 hot flash sale product
    public List<FlashSaleProduct> getTopFlashSaleProduct() throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ,\n" +
                        "fs.StoreId, fs.Discount , fs.EndDate\n" +
                        " from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs \n" +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId \n" +
                        "order by fs.Discount desc limit 10";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int flashSaleProductId = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    int storeProductId = rs.getInt(3);
                    int spQuantity = rs.getInt(5);
                    int storeId = rs.getInt(7);
                    int discount = rs.getInt(8 );
                    float price = rs.getFloat(6);
                    Date endDate = rs.getDate(9);
                    String productName = rs.getString(4);

                    result.add(new FlashSaleProduct(flashSaleProductId,quantity,storeProductId,
                            productName,spQuantity,price, storeId,discount,endDate
                            ));

                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm);
        }
        return result;
    }

    // 6/17/2019 TinLM Create
    // Get top 10 hot flash sale product
    public List<FlashSaleProduct> getFSPByStoreId(int storeId) throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate " +
                        "from Flashsales fs,Store s, StoreProduct sp, FlashsaleProduct fsp \n" +
                        "where fsp.FlashSalesId = fs.FlashSalesId and s.StoreId = sp.StoreId and sp.StoreProductId = fsp.StoreProductId\n" +
                        " and s.StoreId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int flashSaleProductId = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    int storeProductId = rs.getInt(3);
                    int spQuantity = rs.getInt(5);
                    int discount = rs.getInt(8 );
                    float price = rs.getFloat(6);
                    Date endDate = rs.getDate(9);
                    String productName = rs.getString(4);

                    result.add(new FlashSaleProduct(flashSaleProductId,quantity,storeProductId,
                            productName,spQuantity,price, storeId,discount,endDate
                    ));

                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm);
        }
        return result;
    }

    // get all flash sale product
    public List<FlashSaleProduct> getAllFSP() {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ,\n" +
                        "fs.StoreId, fs.Discount , fs.EndDate\n" +
                        " from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs \n" +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId limit 10\n";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int flashSaleProductId = rs.getInt(1);
                    int quantity = rs.getInt(2);
                    int storeProductId = rs.getInt(3);
                    int spQuantity = rs.getInt(5);
                    int storeId = rs.getInt(7);
                    int discount = rs.getInt(8 );
                    float price = rs.getFloat(6);
                    Date endDate = rs.getDate(9);
                    String productName = rs.getString(4);
                    result.add(new FlashSaleProduct(flashSaleProductId,quantity,storeProductId,
                            productName,spQuantity,price, storeId,discount,endDate
                    ));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm);
        }
        return result;
    }

    public List<FlashSaleProduct> getFSPByCategoriesId(int categoryId) throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select fs.StoreId, fs.Discount , fs.EndDate, fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price  " +
                        "from Store s, StoreProduct sp, FlashsaleProduct fsp , Product p, Categories c,  Flashsales fs  \n" +
                        "where s.StoreId = sp.StoreId and sp.StoreProductId = fsp.StoreProductId \n" +
                        " and p.CategoriesId = c.CategoriesId and p.ProductId = sp.ProductId and fs.FlashSalesId = fsp.FlashSalesId " +
                        " and c.CategoriesId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int flashSaleProductId = rs.getInt("FlashSaleProductId");
                    int quantity = rs.getInt("Quantity");
                    int storeProductId = rs.getInt("StoreProductId");
                    int spQuantity = rs.getInt("SpQuantity");
                    int storeId = rs.getInt("StoreId");
                    int discount = rs.getInt("Discount");
                    float price = rs.getFloat("Price");
                    Date endDate = rs.getDate("EndDate");
                    String productName = rs.getString("ProductName");

                    result.add(new FlashSaleProduct(flashSaleProductId,quantity,storeProductId,
                            productName,spQuantity,price, storeId,discount,endDate
                    ));
                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm);
        }
        return result;
    }

}
