package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Repository

public class StoreDAO {


    public List<Store> getAllStore() throws SQLException, ClassNotFoundException {

        List<Store> result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select s.StoreId, s.StoreName,s.StoreManagerId, s.LocationId, s.RatingPoint, s.Avatar," +
                        " s.OpenHour, s.CloseHour,l.Address, d.DistrictName, w.WardName, c.CityName, coun.CountryName," +
                        "s.Latitude, s.Longitude from Store s, Location l, District d, Ward w, City c, Country coun \n" +
                        " where l.DistrictId = d.DistrictId and d.WardId = w.WardId and \n" +
                        " w.CityId = c.CityId and c.CountryId = coun.CountryId and s.LocationId = l.LocationId";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    if(result == null)
                        result = new ArrayList<>();
                    Store store = new Store();
                    store.setStoreId(rs.getInt("StoreId"));
                    store.setAccountId(rs.getInt("StoreManagerId"));
                    store.setLocationId(rs.getInt("LocationId"));
                    store.setStoreName(rs.getString("StoreName"));
                    store.setAvatar(rs.getString("Avatar"));
                    store.setOpenHour(rs.getString("OpenHour"));
                    store.setCloseHour(rs.getString("CloseHour"));
                    store.setLatitude(rs.getDouble("Latitude"));
                    store.setLongitude(rs.getDouble("Longitude"));
                    store.setRatingPoint(rs.getFloat("RatingPoint"));

                    store.setAddress(rs.getString("Address"));
                    store.setWard(rs.getString("WardName"));
                    store.setCity(rs.getString("CityName"));
                    store.setCountry(rs.getString("CountryName"));
                    store.setDistrict(rs.getString("DistrictName"));

                    result.add(store);

                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return result;
    }

    public Store getStoreById(int storeId) throws SQLException, ClassNotFoundException {

        Store result = new Store();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select s.StoreId, s.StoreName,s.accountId, s.Address, s.RatingPoint, s.Avatar," +
                        " s.OpenHour, s.CloseHour," +
                        "s.Latitude, s.Longitude from Store s" +
                        " where" +
                        " s.StoreId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeId);
                rs = stm.executeQuery();
                if (rs.next()){

                    result.setStoreId(rs.getInt("StoreId"));
                    result.setAccountId(rs.getInt("accountId"));
//                    result.setLocationId(rs.getInt("LocationId"));
                    result.setStoreName(rs.getString("StoreName"));
                    result.setAvatar(rs.getString("Avatar"));
                    result.setOpenHour(rs.getString("OpenHour"));
                    result.setCloseHour(rs.getString("CloseHour"));
                    result.setLatitude(rs.getDouble("Latitude"));
                    result.setLongitude(rs.getDouble("Longitude"));
                    result.setRatingPoint(rs.getFloat("RatingPoint"));
                    result.setAddress(rs.getString("Address"));


                }
            }
        }finally {
                MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

}
