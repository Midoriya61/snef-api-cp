package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Location;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getLocatinById
@Repository
public class LocationDAO {
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
    // 6/17/2019 TinLM Create
    // Get getLocatinById
    public Location getLocatinById(int locationId) throws SQLException, ClassNotFoundException {
        Location result =null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select l.[Address], d.DistrictName, w.WardName, c.CityName, coun.CountryName from Location l, District d, Ward w, City c, Country coun \n" +
                        "where l.DistrictId = d.DistrictId and d.WardId = w.WardId and \n" +
                        "w.CityId = c.CityId and c.CountryId = coun.CountryId \n" +
                        "and l.LocationId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, locationId);
                rs = stm.executeQuery();
                if (rs.next()){
                    String address = rs.getString("Address");
                    String wardName = rs.getString("WardName");
                    String cityName = rs.getString("CityName");
                    String countryName = rs.getString("CountryName");
                    String districtName = rs.getString("DistrictName");

                    result  =  new Location(locationId,address, districtName, wardName, cityName, countryName);

                }
            }
        }finally {
            closeConnection();
        }
        return result;
    }
}
