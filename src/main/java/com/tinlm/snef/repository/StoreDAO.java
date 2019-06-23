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

        List<Store> result = new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select StoreId, StoreName,StoreManagerId, LocationId, RatingPoint, Avatar, OpenHour, CloseHour from Store";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int storeId = rs.getInt("StoreId");
                    int storeManagerId = rs.getInt("StoreManagerId");
                    int locationId = rs.getInt("LocationId");
                    String storeName = rs.getString("StoreName");
                    String avatar = rs.getString("Avatar");
                    String openHour = rs.getString("OpenHour");
                    String closeHour = rs.getString("CloseHour");

                    result.add(new Store(storeId, storeManagerId, storeName,  locationId, avatar, openHour, closeHour));

                }
            }
        }finally {
            closeConnection();
        }
        return result;
    }

}
