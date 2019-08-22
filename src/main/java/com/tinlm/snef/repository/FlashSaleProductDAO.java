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
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                        "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi " +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                        "and  sp.StoreProductId = spi.StoreProductId " +
                        "and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) " +
                        "and fs.EndDate > CURDATE() and fsp.Status = True \n" +
                        "order by fs.Discount desc limit 10";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fspId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as TotalQuantity, FlashSaleProductId from OrderDetail \n" +
                            " Where FlashSaleProductId = ? group by snef_part2.OrderDetail.FlashSaleProductId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1,fspId);
                    ResultSet rsQuantity = stm.executeQuery();
                    int totalQuantity = 0;
                    if( rsQuantity.next() ) {
                        totalQuantity = rsQuantity.getInt("TotalQuantity");
                    }
                    rsQuantity.close();
                    FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                    flashSaleProduct.setFlashSaleProductId(fspId);
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setStoreId(rs.getInt(7));
                    flashSaleProduct.setDiscount(rs.getInt(8));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setTotalQuantity(totalQuantity);
                    flashSaleProduct.setImageSrc(rs.getString(10));
                    flashSaleProduct.setDescription(rs.getString(11));
                    result.add(flashSaleProduct);
                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm,con);
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
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc " +
                        "from Flashsales fs,Store s, StoreProduct sp, FlashsaleProduct fsp, StoreProductImage spi  \n" +
                        "where fsp.FlashSalesId = fs.FlashSalesId and s.StoreId = sp.StoreId and sp.StoreProductId = fsp.StoreProductId\n" +
                        " and  sp.StoreProductId = spi.StoreProductId " +
                        " and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) " +
                        "and sp.Status = True and fs.EndDate > CURDATE() and fsp.Status = True and s.StoreId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeId);
                rs = stm.executeQuery();
                while (rs.next()){
                    FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                    flashSaleProduct.setFlashSaleProductId(rs.getInt(1));
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setDiscount(rs.getInt(8 ));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setImageSrc(rs.getString(10));

                    result.add(flashSaleProduct);
                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm,con);
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
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                        "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi " +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                        "and sp.StoreProductId = spi.StoreProductId " +
                        "and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) " +
                        "and fs.EndDate > CURDATE() and fsp.Status = True";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fspId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as TotalQuantity, FlashSaleProductId from OrderDetail \n" +
                            " Where FlashSaleProductId = ? group by snef_part2.OrderDetail.FlashSaleProductId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1,fspId);
                    ResultSet rsQuantity = stm.executeQuery();
                    int totalQuantity = 0;
                    if( rsQuantity.next() ) {
                        totalQuantity = rsQuantity.getInt("TotalQuantity");
                    }
                    rsQuantity.close();
                    FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                    flashSaleProduct.setFlashSaleProductId(fspId);
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setStoreId(rs.getInt(7));
                    flashSaleProduct.setDiscount(rs.getInt(8));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setTotalQuantity(totalQuantity);
                    flashSaleProduct.setImageSrc(rs.getString(10));
                    flashSaleProduct.setDescription(rs.getString(11));
                    result.add(flashSaleProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

    public List<FlashSaleProduct> getFSPByCategoriesId(int categoryId) throws SQLException {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                        "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi,Categories c, Product p " +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                        "and sp.StoreProductId = spi.StoreProductId " +
                        "and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) " +
                        " and p.CategoriesId = c.CategoriesId and p.ProductId = sp.ProductId and fs.EndDate > CURDATE()" +
                        " and fsp.Status = True and c.CategoriesId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fspId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as TotalQuantity, FlashSaleProductId from OrderDetail \n" +
                            " Where FlashSaleProductId = ? group by snef_part2.OrderDetail.FlashSaleProductId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1,fspId);
                    ResultSet rsQuantity = stm.executeQuery();
                    int totalQuantity = 0;
                    if( rsQuantity.next() ) {
                        totalQuantity = rsQuantity.getInt("TotalQuantity");
                    }
                    rsQuantity.close();
                    FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                    flashSaleProduct.setFlashSaleProductId(fspId);
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setStoreId(rs.getInt(7));
                    flashSaleProduct.setDiscount(rs.getInt(8));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setTotalQuantity(totalQuantity);
                    flashSaleProduct.setImageSrc(rs.getString(10));
                    flashSaleProduct.setDescription(rs.getString(11));
                    result.add(flashSaleProduct);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

    public int getRemaingQuantity(int fspId)  throws SQLException {
        int result = 0;
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = " select fsp.FlashSaleProductId, (fsp.Quantity - od.totalQuantity) as RemaingQuantity " +
                        "from FlashsaleProduct fsp, " +
                        "(select FlashSaleProductId, SUM(Quantity) as totalQuantity from OrderDetail group by  FlashSaleProductId) as od " +
                        "where od.FlashSaleProductId = fsp.FlashSaleProductId " +
                        " and fsp.FlashSaleProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, fspId);
                rs = stm.executeQuery();
                while (rs.next()){
                    result = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

    // get flash sale product by search name
    public List<FlashSaleProduct> getFSPByName(String searchName, String searchCategories) {
        List<FlashSaleProduct> result = new ArrayList<>();
        Connection con =null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String[] listSearchName = searchName.split(" ");
                //change name for fit dbms
                String searchNameProduct = "";
                for (int i = 0; i < listSearchName.length; i++) {
                    if (i == 0) {
                        searchNameProduct = "sp.ProductName like N'%" + listSearchName[0] + "%' ";
                    } else {
                        searchNameProduct = searchNameProduct + "AND sp.ProductName like N'%" + listSearchName[i] + "%' ";
                    }
                }
                String sql = "";
                if(searchCategories.equals("all")) {
                    sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                            "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                            "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi " +
                            "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                                "and sp.StoreProductId = spi.StoreProductId and fs.EndDate > CURDATE() and fsp.Status = True " +
                            "and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) and " +
                            searchNameProduct +
                            "limit 30";
                } else {
                    String searchCategorie = searchCategories.substring(0, searchCategories.length() - 1);
                    sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                            "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                            "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi," +
                            "Categories c, Product p " +
                            "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                            "and sp.StoreProductId = spi.StoreProductId and fs.EndDate > CURDATE() and fsp.Status = True " +
                            "and c.CategoriesId = p.CategoriesId and p.ProductId = sp.ProductId  " +
                            "and c.CategoriesId in (" + searchCategorie +  ")" +
                            "and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1) and " +
                            searchNameProduct +
                            "limit 30";
                }

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fspId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as TotalQuantity, FlashSaleProductId from OrderDetail \n" +
                            " Where FlashSaleProductId = ? group by snef_part2.OrderDetail.FlashSaleProductId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1,fspId);
                    ResultSet rsQuantity = stm.executeQuery();
                    int totalQuantity = 0;
                    if( rsQuantity.next() ) {
                        totalQuantity = rsQuantity.getInt("TotalQuantity");
                    }
                    rsQuantity.close();
                    FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                    flashSaleProduct.setFlashSaleProductId(fspId);
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setStoreId(rs.getInt(7));
                    flashSaleProduct.setDiscount(rs.getInt(8));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setTotalQuantity(totalQuantity);
                    flashSaleProduct.setImageSrc(rs.getString(10));
                    flashSaleProduct.setDescription(rs.getString(11));
                    result.add(flashSaleProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

    public FlashSaleProduct getFSPById(int flashSaleProductId) throws SQLException, ClassNotFoundException {

        FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select  fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ," +
                        "fs.StoreId, fs.Discount , fs.EndDate, spi.ImageSrc, sp.Description " +
                        "from FlashsaleProduct fsp, StoreProduct sp, Flashsales fs , StoreProductImage spi " +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId " +
                        "and  sp.StoreProductId = spi.StoreProductId and fsp.FlashSaleProductId = ? " +
                        " and spi.SPIId = (select SPIId from StoreProductImage where StoreProductId = sp.StoreProductId limit 1)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, flashSaleProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    int fspId = rs.getInt(1);
                    String sqlQuantity = "select SUM(Quantity) as TotalQuantity, FlashSaleProductId from OrderDetail \n" +
                            " Where FlashSaleProductId = ? group by snef_part2.OrderDetail.FlashSaleProductId";
                    stm = con.prepareStatement(sqlQuantity);
                    stm.setInt(1,fspId);
                    ResultSet rsQuantity = stm.executeQuery();
                    int totalQuantity = 0;
                    if( rsQuantity.next() ) {
                        totalQuantity = rsQuantity.getInt("TotalQuantity");
                    }
                    rsQuantity.close();
                    flashSaleProduct.setFlashSaleProductId(fspId);
                    flashSaleProduct.setQuantity(rs.getInt(2));
                    flashSaleProduct.setStoreProductId(rs.getInt(3));
                    flashSaleProduct.setSpQuantity(rs.getInt(5));
                    flashSaleProduct.setStoreId(rs.getInt(7));
                    flashSaleProduct.setDiscount(rs.getInt(8));
                    flashSaleProduct.setPrice(rs.getFloat(6));
                    flashSaleProduct.setEndDate(rs.getDate(9));
                    flashSaleProduct.setProductName(rs.getString(4));
                    flashSaleProduct.setTotalQuantity(totalQuantity);
                    flashSaleProduct.setImageSrc(rs.getString(10));
                    flashSaleProduct.setDescription(rs.getString(11));

                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return flashSaleProduct;
    }

}
