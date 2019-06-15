package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.ProductFlashSales;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductFlashSalesDAO implements Serializable {

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

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
    public List<ProductFlashSales> loadFsToDay() throws SQLException, ClassNotFoundException {

        List<ProductFlashSales> flashSales = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){

                // convet varchar to Date type YYYY-MM-DD
//                CONVERT(DATE,LEFT(REPLACE(EndDate, '/', ''),4)
//                        +SUBSTRING(REPLACE(EndDate, '/', ''),5,2)+RIGHT(REPLACE(EndDate, '/', ''),2))
                String sql =
                        "SELECT fs.ProductFlashSalesId, fs.Discount, fs.StartDate, fs.EndDate, fs.Quantity, fs.StoreProductId, fs.StoreId, " +
                                "p.Price, p.StoreProductName, p.Picture " +
                                "FROM FlashSalesProduct fs, StoreProduct p " +
                                "WHERE fs.StoreProductId = p.StoreProductId " +
                                "AND CONVERT(DATE , GETDATE()) >= CONVERT(DATE,LEFT(REPLACE(fs.StartDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.StartDate, '/', ''),5,2)+RIGHT(REPLACE(fs.StartDate, '/', ''),2)) " +
                                "AND CONVERT(DATE,LEFT(REPLACE(fs.EndDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.EndDate, '/', ''),5,2)+RIGHT(REPLACE(fs.EndDate, '/', ''),2)) >=  CONVERT(DATE , GETDATE())";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fsId = rs.getInt("ProductFlashSalesId");
                    int discount = rs.getInt("Discount");
                    String startdate = rs.getString("StartDate");
                    String enddate = rs.getString("EndDate");
                    int quantity = rs.getInt("Quantity");
                    int productitemid = rs.getInt("StoreProductId");
                    int accountid = rs.getInt("StoreId");
                    float price = rs.getFloat("Price");
                    String itemName = rs.getString("StoreProductName");
                    String image = rs.getString("Picture");

                    ProductFlashSales dto = new ProductFlashSales(fsId, discount, startdate, enddate, quantity, productitemid, accountid, price, itemName, image);
                    if (flashSales == null){
                        flashSales = new ArrayList<>();
                    }
                    flashSales.add(dto);

                }
                return flashSales;
            }
        }finally {
            closeConnection();

        }
        return null;
    }

    public List<ProductFlashSales> loadFsTomorow() throws SQLException, ClassNotFoundException {

        List<ProductFlashSales> flashSales = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql =
                        "SELECT fs.ProductFlashSalesId, fs.Discount, fs.StartDate, fs.EndDate, fs.Quantity, fs.StoreProductId, fs.StoreId, " +
                                "p.Price, p.StoreProductName, p.Picture " +
                                "FROM FlashSalesProduct fs, StoreProduct p " +
                                "WHERE fs.StoreProductId = p.StoreProductId " +
                                "AND CONVERT(DATE , GETDATE() + 1) >= CONVERT(DATE,LEFT(REPLACE(fs.StartDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.StartDate, '/', ''),5,2)+RIGHT(REPLACE(fs.StartDate, '/', ''),2)) " +
                                "AND CONVERT(DATE,LEFT(REPLACE(fs.EndDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.EndDate, '/', ''),5,2)+RIGHT(REPLACE(fs.EndDate, '/', ''),2)) >=  CONVERT(DATE , GETDATE() + 1)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fsId = rs.getInt("ProductFlashSalesId");
                    int discount = rs.getInt("Discount");
                    String startdate = rs.getString("StartDate");
                    String enddate = rs.getString("EndDate");
                    int quantity = rs.getInt("Quantity");
                    int productitemid = rs.getInt("StoreProductId");
                    int accountid = rs.getInt("StoreId");
                    float price = rs.getFloat("Price");
                    String itemName = rs.getString("StoreProductName");
                    String image = rs.getString("Picture");

                    ProductFlashSales dto = new ProductFlashSales(fsId, discount, startdate, enddate, quantity, productitemid, accountid, price, itemName, image);
                    if (flashSales == null){
                        flashSales = new ArrayList<>();
                    }
                    flashSales.add(dto);

                }
                return flashSales;
            }
        }finally {

            closeConnection();
        }
        return null;
    }

    public List<ProductFlashSales> loadFsNext() throws SQLException, ClassNotFoundException {

        List<ProductFlashSales> flashSales = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql =
                        "SELECT fs.ProductFlashSalesId, fs.Discount, fs.StartDate, fs.EndDate, fs.Quantity, fs.StoreProductId, fs.StoreId, " +
                                "p.Price, p.StoreProductName, p.Picture " +
                                "FROM FlashSalesProduct fs, StoreProduct p " +
                                "WHERE fs.StoreProductId = p.StoreProductId " +
                                "AND CONVERT(DATE , GETDATE() + 2) >= CONVERT(DATE,LEFT(REPLACE(fs.StartDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.StartDate, '/', ''),5,2)+RIGHT(REPLACE(fs.StartDate, '/', ''),2)) " +
                                "AND CONVERT(DATE,LEFT(REPLACE(fs.EndDate, '/', ''),4) " +
                                "+SUBSTRING(REPLACE(fs.EndDate, '/', ''),5,2)+RIGHT(REPLACE(fs.EndDate, '/', ''),2)) >=  CONVERT(DATE , GETDATE() + 2)";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fsId = rs.getInt("ProductFlashSalesId");
                    int discount = rs.getInt("Discount");
                    String startdate = rs.getString("StartDate");
                    String enddate = rs.getString("EndDate");
                    int quantity = rs.getInt("Quantity");
                    int productitemid = rs.getInt("StoreProductId");
                    int accountid = rs.getInt("StoreId");
                    float price = rs.getFloat("Price");
                    String itemName = rs.getString("StoreProductName");
                    String image = rs.getString("Picture");

                    ProductFlashSales dto = new ProductFlashSales(fsId, discount, startdate, enddate, quantity, productitemid, accountid, price, itemName, image);
                    if (flashSales == null){
                        flashSales = new ArrayList<>();
                    }
                    flashSales.add(dto);

                }
                return flashSales;
            }
        }finally {
            closeConnection();
        }
        return null;
    }

    public List<ProductFlashSales> searchFSById(int fsId) throws SQLException, ClassNotFoundException {
        List<ProductFlashSales> searchValue = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql =
                        "SELECT f.ProductFlashSalesid, f.Discount, f.StartDate, f.EndDate, f.Quantity, f.StoreId, f.StoreProductId " +
                        "FROM dbo.FlashSalesProduct f " +
                        "WHERE f.ProductFlashSalesid = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, fsId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int profsId = rs.getInt("ProductFlashSalesId");
                    int discount = rs.getInt("Discount");
                    String startdate = rs.getString("StartDate");
                    String enddate = rs.getString("EndDate");
                    int quantity = rs.getInt("Quantity");
                    int storeId = rs.getInt("StoreId");
                    int storeProductId = rs.getInt("StoreProductId");


                    ProductFlashSales dto = new ProductFlashSales(profsId, discount, startdate, enddate,quantity, storeId, storeProductId );
                    if (searchValue == null){
                        searchValue = new ArrayList<>();
                    }
                    searchValue.add(dto);

                }
                return searchValue;
            }
        }finally {
            closeConnection();
        }
        return null;
    }
}
