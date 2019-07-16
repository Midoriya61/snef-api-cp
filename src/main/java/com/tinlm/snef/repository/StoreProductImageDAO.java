package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.StoreProductImage;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 6/21/2019 TinLM Create class
// 6/21/2019 TinLM Create getOneStoreProductImageById
// 6/21/2019 TinLM Create getStoreProductImageById
@Repository
public class StoreProductImageDAO {




    // 6/21/2019 TinLM Create
    // Get getOneStoreProductImageById
    public StoreProductImage getOneStoreProductImageById(int storeProductId) throws SQLException, ClassNotFoundException {
        StoreProductImage result =null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select SPIId, ImageSrc from StoreProductImage where StoreProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    int fspId = rs.getInt("SPIId");
                    String imageSrc = rs.getString("ImageSrc");
                    result  =  new StoreProductImage(fspId, imageSrc, storeProductId);

                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm, con);
        }
        return result;
    }

    // 6/21/2019 TinLM Create
    // Get getStoreProductImageById
    public List<String> getStoreProductImageById(int storeProductId) throws SQLException, ClassNotFoundException {
        List<String> result =new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select SPIId, ImageSrc from StoreProductImage where StoreProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeProductId);
                rs = stm.executeQuery();
                while (rs.next()){
                    result.add(rs.getString("ImageSrc"));

                }
            }
        }finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

}
