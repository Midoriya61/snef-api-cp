package com.tinlm.snef.repository;

import com.tinlm.snef.connection.MyConnection;
import com.tinlm.snef.model.Product;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO implements Serializable {

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

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

    /*
     * Load all Product using dbo.Product
     * */
    public List<Product> loadAllProduct() throws SQLException, ClassNotFoundException {

        List<Product> products = new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "SELECT p.productid, p.productname,p.description,p.picture,p.categoriesid FROM product p";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int pro = rs.getInt("productid");
                    String proName = rs.getString("productname");
                    String des = rs.getString("description");
                    String pic = rs.getString("picture");
                    int catId = rs.getInt("categoriesid");

                    Product dto = new Product(pro, proName, des, pic, catId);

                    products.add(dto);

                }
            }
        }finally {
            closeConnection();
        }
        return products;
    }

}
