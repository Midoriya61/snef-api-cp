package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository

public class StoreDAO {

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

    public List<Store> getAllStore() throws SQLException, ClassNotFoundException {

        List<Store> result = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "SELECT StoreId, StoreName,StoreManagerId, LocationId, RatingPoint, Avatar, OpenHour, CloseHour, Status " +
                        "FROM Store";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int storeId = rs.getInt("StoreId");
                    String storeName = rs.getString("StoreName");
                    int storeManagerId = rs.getInt("StoreManagerId");
                    int loId = rs.getInt("LocationId");
                    float rating = rs.getFloat("RatingPoint");
                    String avatar = rs.getString("Avatar");
                    String open = rs.getString("OpenHour");
                    String close =rs.getString("CloseHour");
                    boolean status = rs.getBoolean("Status");

                    Store dto = new Store(storeId, storeName, storeManagerId, loId, rating, avatar, open, close, status);
                    if (result == null){
                        result = new ArrayList<>();
                    }
                    result.add(dto);
                }
                return  result;
            }
        }finally {
            closeConnection();
        }
        return null;
    }

}
