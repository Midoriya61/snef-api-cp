package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.StoreFollower;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreFollowerDAO implements Serializable {


    public List<StoreFollower> getFollowByCusId(int customerId) throws SQLException, ClassNotFoundException {
        List<StoreFollower> listFollow = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "SELECT TOP(8) StoreFollower ,StoreId, CustomerId FROM dbo.StoreFollwer WHERE dbo.StoreFollwer.CustomerId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, customerId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int storeFollow = rs.getInt("StoreFollower");
                    int storeId = rs.getInt("StoreId");
                    int custId = rs.getInt("CustomerId");
                    StoreFollower dto = new StoreFollower(storeFollow, storeId, custId);
                    if(listFollow == null){
                        listFollow = new ArrayList<>();
                    }
                    listFollow.add(dto);
                }
                return  listFollow;
            }
        }finally {
            MyConnection.closeConnection(rs, stm,con);
        }
        return null;
    }

    public boolean insertNewFollower(int storeId, int custId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
           con = MyConnection.myConnection();
           if (con != null){
               String sql = "INSERT INTO dbo.StoreFollwer(StoreId, CustomerId) VALUES ( ?, ?)";
               stm = con.prepareStatement(sql);
               stm.setInt(1,storeId);
               stm.setInt(2, custId);
               int row = stm.executeUpdate();
               if (row > 0){
                   return true;
               }

           }

        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return false;
    }

    public boolean deleteFollower(int storeId, int custId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "DELETE FROM dbo.StoreFollwer WHERE StoreId = ? AND CustomerId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeId);
                stm.setInt(2, custId);
                int row = stm.executeUpdate();
                if (row > 0){
                    return  true;
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return false;
    }


}
