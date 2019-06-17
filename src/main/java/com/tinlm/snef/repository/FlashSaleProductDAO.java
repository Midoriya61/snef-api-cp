package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.FlashSaleProduct;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create
@Repository
public class FlashSaleProductDAO {
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
    // 6/17/2019 TinLM Create
    // Get top 10 hot flash sale product
    public List<FlashSaleProduct> getTopFlashSaleProduct() throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select top 10 fsp.FlashSaleProductId, fsp.Quantity, sp.StoreProductId, sp.ProductName, sp.Quantity as SpQuantity, sp.Price ,\n" +
                        "fs.StoreId, fs.Discount , fs.EndDate\n" +
                        " from FlashSaleProduct fsp, StoreProduct sp, FlashSales fs \n" +
                        "where fsp.FlashSalesId = fs.FlashSalesId and fsp.StoreProductId = sp.StoreProductId \n" +
                        "order by fs.Discount desc";
                stm = con.prepareStatement(sql);
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
            closeConnection();
        }
        return result;
    }
}
