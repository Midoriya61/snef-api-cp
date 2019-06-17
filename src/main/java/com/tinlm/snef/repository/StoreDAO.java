package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository

public class StoreDAO {

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

    public List<Store> getAllStore() throws SQLException, ClassNotFoundException {

        List<Store> result = new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select StoreId, AccountId, LocationId, RatingPoint, Avatar, OpenHour, CloseHour from Store";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int storeId = rs.getInt("StoreId");
                    int accountId = rs.getInt("AccountId");
                    int locationId = rs.getInt("LocationId");

                    String avatar = rs.getString("Avatar");
                    String openHour = rs.getString("OpenHour");
                    String closeHour = rs.getString("CloseHour");

                    result.add(new Store(storeId, accountId, locationId, avatar, openHour, closeHour));

                }
            }
        }finally {
            closeConnection();
        }
        return result;
    }

}
