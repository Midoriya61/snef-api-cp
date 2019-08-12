package com.tinlm.snef.repository;

// 6/22/2019 TinLM Create class

import com.tinlm.snef.connection.MyConnection;

import com.tinlm.snef.model.Customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerDAO implements AccountDAO {


    @Override
    public Customer login(String username, String password)  {
        Customer result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "select acc.AccountId, acc.Username, acc.FirstName, acc.LastName, acc.Phone, acc.Email, acc.Avatar, acc.IsActive " +
                        "from Account acc WHERE acc.UserName = ? and acc.Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()){
                    result = new Customer();
                    result.setUserName(rs.getString("UserName"));
                    result.setFirstName(rs.getString("FirstName"));
                    result.setAccountId(rs.getInt("AccountId"));
                    result.setLastName(rs.getString("LastName"));
                    result.setPhone(rs.getString("Phone"));
                    result.setEmail(rs.getString("Email"));
                    result.setAvatar(rs.getString("Avatar"));
                    result.setActive(rs.getBoolean("IsActive"));
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

    @Override
    public Boolean createAccount(String username, String password, String firstname, String lastname) {
        Boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "insert into `Account` (`Username`,`Password`,`FirstName`,`LastName`)  \n" +
                        "values (?,?,?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, firstname);
                stm.setString(4, lastname);
                int reesult = stm.executeUpdate();
                if( reesult > 0 ) {
                    sql = "select AccountId from Account where Username = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1,username);
                    rs = stm.executeQuery();
                    if(rs.next()) {
                        sql = "insert into `Customer`(`AccountId`) values (?)";
                        stm = con.prepareStatement(sql);
                        stm.setInt(1,rs.getInt(1));
                        result = stm.executeUpdate() > 0;
                    }

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

}
