package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Store;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StoreDAO {

    //DEGREES đổi số thành độ
    // ACOS là cosin
    // LEAST trả về giá trị nhỏ nhất trong danh sách

    public List<Store> getAllStore(double latitude, double longitude) throws SQLException, ClassNotFoundException {

        List<Store> result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                    String sql = "select s.StoreId, s.StoreName,s.accountId, od.rp as RatingPoint, s.Avatar," +
                            "s.OpenHour, s.CloseHour,s.Address," +
                            "s.Latitude,s.Longitude, s.Phone, " +
                            "111.111 * ST_Distance(Point(s.Latitude, s.Longitude), Point(?,?)) as distance_in_km \n" +
                            "from Store s, " +
                            "(select storeId, avg(RatingPoint) as rp" +
                            " from snef_part2.Order" +
                            " where RatingPoint != 0" +
                            " group by storeId) as od where s.Status = True and " +
                            "s.StoreId = od.storeId " +
                            "Order by distance_in_km asc limit 10";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, latitude);
                stm.setDouble(2, longitude);
                rs = stm.executeQuery();
                while (rs.next()){
                    if(result == null)
                        result = new ArrayList<>();
                    Store store = new Store();
                    store.setStoreId(rs.getInt("StoreId"));
                    store.setAccountId(rs.getInt("accountId"));
                    store.setStoreName(rs.getString("StoreName"));
                    store.setAvatar(rs.getString("Avatar"));
                    store.setOpenHour(rs.getString("OpenHour"));
                    store.setCloseHour(rs.getString("CloseHour"));
                    store.setLatitude(rs.getDouble("Latitude"));
                    store.setLongitude(rs.getDouble("Longitude"));
                    store.setRatingPoint(rs.getFloat("RatingPoint"));
                    store.setAddress(rs.getString("Address"));
                    store.setPhone(rs.getString("Phone"));
                    store.setDistance(rs.getDouble("distance_in_km"));
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
                String sql = "select s.StoreId, s.StoreName,s.accountId, od.rp as RatingPoint, s.Avatar," +
                        "s.OpenHour, s.CloseHour,s.Address," +
                        "s.Latitude, s.Longitude, s.Phone " +
                        "from Store s, " +
                        "(select storeId, avg(RatingPoint) as rp from snef_part2.Order " +
                        "where RatingPoint != 0 " +
                        " group by storeId) as od " +
                        "where  s.StoreId = ? and s.StoreId = od.storeId";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result.setStoreId(rs.getInt("StoreId"));
                    result.setAccountId(rs.getInt("accountId"));
                    result.setStoreName(rs.getString("StoreName"));
                    result.setAvatar(rs.getString("Avatar"));
                    result.setOpenHour(rs.getString("OpenHour"));
                    result.setCloseHour(rs.getString("CloseHour"));
                    result.setLatitude(rs.getDouble("Latitude"));
                    result.setLongitude(rs.getDouble("Longitude"));
                    result.setRatingPoint(rs.getFloat("RatingPoint"));
                    result.setAddress(rs.getString("Address"));
                    result.setPhone(rs.getString("Phone"));

                }
            }
        }finally {
                MyConnection.closeConnection(rs, stm,con);
        }
        return result;
    }

    public List<Store> getStoreByDistance ( double latitude, double longitude, double distance )
            throws SQLException, ClassNotFoundException {

        List<Store> result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select s.StoreId, s.StoreName,s.accountId, od.rp as RatingPoint, s.Avatar," +
                        "s.OpenHour, s.CloseHour,s.Address," +
                        "s.Latitude,s.Longitude, s.Phone, " +
                        "111.111 * ST_Distance(Point(s.Latitude, s.Longitude), Point(?,?)) as distance_in_km \n" +
                        "from Store s, " +
                        "(select storeId, avg(RatingPoint) as rp from snef_part2.Order " +
                        "where RatingPoint != 0 " +
                        " group by storeId) as od where s.Status = True and " +
                        "s.StoreId = od.storeId " +
                        "having distance_in_km <= " + distance +
                        " Order by distance_in_km asc limit 10";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, latitude);
                stm.setDouble(2, longitude);
                rs = stm.executeQuery();
                while (rs.next()){
                    if(result == null)
                        result = new ArrayList<>();
                    Store store = new Store();
                    store.setStoreId(rs.getInt("StoreId"));
                    store.setAccountId(rs.getInt("accountId"));
                    store.setStoreName(rs.getString("StoreName"));
                    store.setAvatar(rs.getString("Avatar"));
                    store.setOpenHour(rs.getString("OpenHour"));
                    store.setCloseHour(rs.getString("CloseHour"));
                    store.setLatitude(rs.getDouble("Latitude"));
                    store.setLongitude(rs.getDouble("Longitude"));
                    store.setRatingPoint(rs.getFloat("RatingPoint"));

                    store.setAddress(rs.getString("Address"));
                    store.setPhone(rs.getString("Phone"));
                    store.setDistance(rs.getDouble("distance_in_km"));
                    result.add(store);

                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return result;
    }



}
