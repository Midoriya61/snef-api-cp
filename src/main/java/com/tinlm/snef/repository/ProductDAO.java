package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO implements Serializable {
    public List<Product> getListNameProduct() throws SQLException, ClassNotFoundException {
        List<Product> result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.myConnection();
            if (con != null){
                String sql ="select ProductId, ProductName from Product";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while ( rs.next() ) {
                    if(result == null)
                        result = new ArrayList<>();
                    Product product = new Product();
                    product.setProductId(rs.getInt(1));
                    product.setProductName(rs.getString(2));
                    result.add(product);
                }
            }
        }finally {
            MyConnection.closeConnection(rs,stm,con);
        }
        return result;
    }
}
