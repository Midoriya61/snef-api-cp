package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailDAO {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public int getQuantityByFSPId(int flashsaleProductId) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select SUM(Quantity) as TotalQuantity from OrderDetail where FlashSaleProductId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, flashsaleProductId);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = rs.getInt("TotalQuantity");
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm, con);
        }
        return result;
    }
}
