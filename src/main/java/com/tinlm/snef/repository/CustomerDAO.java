package com.tinlm.snef.repository;

// 6/22/2019 TinLM Create class

import com.tinlm.snef.connection.MyConnection;

import com.tinlm.snef.model.Customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerDAO {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection()  {
        try {
            if (rs != null){
                rs.close();
            }
            if (stm !=null){
                stm.close();
            }
            if (con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public Customer loginCustomer(String username, String password)  {
//        Customer result = null;
//        try {
//            con = MyConnection.myConnection();
//            if (con !=null){
//                String sql = "select acc.FirstName, acc.LastName, acc.Phone, acc.Email, acc.Avatar, cus.CustomerId, acc.IsActive " +
//                        " from Account acc, Customer cus " +
//                        "where acc.AccountId = cus.AccountId and acc.UserName = ? and acc.[Password] = ?";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                stm.setString(2, password);
//                rs = stm.executeQuery();
//                if (rs.next()){
//                    result = new Customer();
//                    result.setFirstName(rs.getString("FirstName"));
//                    result.setLastName(rs.getString("LastName"));
//                    result.setPhone(rs.getString("Phone"));
//                    result.setEmail(rs.getString("Email"));
//                    result.setAvatar(rs.getString("Avatar"));
//                    result.setCustomerId(rs.getInt("CustomerId"));
//                    result.setActive(rs.getBoolean("IsActive"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return result;
//    }

}
