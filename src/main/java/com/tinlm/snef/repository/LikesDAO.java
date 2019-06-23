package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Likes;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LikesDAO implements Serializable {
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

    public List<Likes> getLikeByProductId(int proId) throws SQLException, ClassNotFoundException {
        List<Likes> getListLikes = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "SELECT TOP(8) lk.LikeId, lk.CustomerId, lk.StoreProductId FROM dbo.StoreProduct as sp, dbo.Likes lk WHERE sp.StoreProductId = lk.StoreProductId and sp.ProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, proId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int likeId = rs.getInt("LikeId");
                    int cusId = rs.getInt("CustomerId");
                    int storeProId = rs.getInt("StoreProductId");

                    Likes dto = new Likes(likeId, cusId, storeProId);
                    if (getListLikes == null){
                        getListLikes = new ArrayList<>();
                    }
                    getListLikes.add(dto);

                }
                return  getListLikes;

            }
        }finally {
            closeConnection();
        }
        return null;
    }

    public boolean deleteLKByProId(int stProId, int cusId) throws SQLException, ClassNotFoundException {
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "DELETE FROM Likes WHERE StoreProductId = ? and CustomerId = ?";
                stm =con.prepareStatement(sql);
                stm.setInt(1, stProId);
                stm.setInt(2, cusId);
                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }finally {
            closeConnection();
        }
        return false;
    }
}
