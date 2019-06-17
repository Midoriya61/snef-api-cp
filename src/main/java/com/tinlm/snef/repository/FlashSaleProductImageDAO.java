package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.FlashSaleProductImage;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getOneFlashSaleProductImageById
// 6/17/2019 TinLM Create getFlashSaleProductImageById
@Repository
public class FlashSaleProductImageDAO {

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
    // Get getOneFlashSaleProductImageById
    public FlashSaleProductImage getOneFlashSaleProductImageById(int flashSaleProductId) throws SQLException, ClassNotFoundException {
        FlashSaleProductImage result =null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select FSPId, ImageSrc from FlashSaleProductImage where FlashSaleProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, flashSaleProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    int fspId = rs.getInt("FSPId");
                    String imageSrc = rs.getString("ImageSrc");


                    result  =  new FlashSaleProductImage(fspId, imageSrc, flashSaleProductId);

                }
            }
        }finally {
            closeConnection();
        }
        return result;
    }

    // 6/17/2019 TinLM Create
    // Get getFlashSaleProductImageById
    public List<FlashSaleProductImage> getFlashSaleProductImageById(int flashSaleProductId) throws SQLException, ClassNotFoundException {
        List<FlashSaleProductImage> result =new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select FSPId, ImageSrc from FlashSaleProductImage where FlashSaleProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, flashSaleProductId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int fspId = rs.getInt("FSPId");
                    String imageSrc = rs.getString("ImageSrc");

                    result.add(new FlashSaleProductImage(fspId, imageSrc, flashSaleProductId));

                }
            }
        }finally {
            closeConnection();
        }
        return result;
    }
}
