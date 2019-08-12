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

    public Likes getLikeById(int accountId, int storeProductId) {
        Likes result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = " select LikeId, Account, StoreProductId from `Like` where accountId = ? and StoreProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                stm.setInt(2, storeProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = new Likes(rs.getInt("LikeId"),rs.getInt("AccountId"), rs.getInt("StoreProductId"));
                               }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(rs,stm,con);
        }

        return result;
    }

    public List<Likes> getLikeByProductId(int proId) throws SQLException, ClassNotFoundException {
        List<Likes> getListLikes = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "SELECT TOP(8) lk.LikeId, lk.AccountId, lk.StoreProductId FROM dbo.StoreProduct as sp, dbo.Likes lk WHERE sp.StoreProductId = lk.StoreProductId and sp.ProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, proId);
                rs = stm.executeQuery();
                while (rs.next()){
                    int likeId = rs.getInt("LikeId");
                    int accountId = rs.getInt("AccountId");
                    int storeProId = rs.getInt("StoreProductId");
                    Likes dto = new Likes(likeId, accountId, storeProId);
                    if (getListLikes == null){
                        getListLikes = new ArrayList<>();
                    }
                    getListLikes.add(dto);

                }
                return  getListLikes;
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return null;
    }

    public boolean deleteLKByProId(int productId, int accountId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "DELETE dbo.Likes " +
                        "FROM dbo.Likes " +
                        "INNER JOIN dbo.StoreProduct " +
                        "ON dbo.Likes.StoreProductId = dbo.StoreProduct.StoreProductId " +
                        "WHERE dbo.StoreProduct.ProductId = ? " +
                        "AND dbo.Likes.AccountId = ?";
                stm =con.prepareStatement(sql);
                stm.setInt(1, productId);
                stm.setInt(2, accountId);
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

    public boolean insertLikeByProId(int accountId, int storeProductId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "insert into `Like`(`AccountId`, `StoreProductId`) values(?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                stm.setInt(2,storeProductId);
                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return  false;
    }

    public boolean deleteLikeById(int likeId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "Delete from `Like` where LikeId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, likeId);

                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return  false;
    }
}
