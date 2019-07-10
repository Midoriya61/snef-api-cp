package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.StoreProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 6/23/2019 TinLM Create class
// 6/23/2019 TinLM Create getQuantityById
public class StoreProductDAO {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;


    public int getQuantityById(int storeProductId) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select Quantity from StoreProduct where StoreProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = rs.getInt("Quantity");
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm);
        }
        return result;
    }

    public String getDescriptionById(int storeProductId) throws SQLException, ClassNotFoundException {
        String result = "";
        try {
            con = MyConnection.getConnection();
            if (con !=null){
                String sql = "select Description from StoreProduct where StoreProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = rs.getString("Description");
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm);
        }
        return result;
    }
}
