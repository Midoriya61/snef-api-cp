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


public class ProductDAO implements Serializable {

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

    /*
     * Load all Product using dbo.Product
     * */
    public List<Product> loadAllProduct() throws SQLException, ClassNotFoundException {

        List<Product> products = new ArrayList<>();
        try {
            con = MyConnection.myConnection();
            if (con !=null){
                String sql = "SELECT p.ProductId, p.CategoriesId, p.ProductName, p.ImageSrc FROM dbo.Product p";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    int pro = rs.getInt("ProductId");
                    String proName = rs.getString("ProductName");
                    String pic = rs.getString("ImageSrc");
                    int catId = rs.getInt("CategoriesId");

                    Product dto = new Product(pro, proName, pic, catId);

                    products.add(dto);

                }
            }
        }finally {
            closeConnection();
        }
        return products;
    }

    /*
     * Search Product name using dbo.product
     * */

    public List<Product> searchProByName(String proName) throws SQLException, ClassNotFoundException{
        List<Product> searchValue = null;
        try {
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "SELECT p.ProductId, p.CategoriesId, p.ProductName, p.ImageSrc " +
                        "FROM Product p " +
                        "WHERE p.ProductName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + proName + "%");
                rs = stm.executeQuery();
                while (rs.next()){
                    int proId = rs.getInt("ProductId");
                    String productname = rs.getString("ProductName");
                    String pic = rs.getString("ImageSrc");
                    int cate = rs.getInt("CategoriesId");

                    Product dto = new Product(proId, productname, pic, cate);
                    if (searchValue == null){
                        searchValue = new ArrayList<>();
                    }
                    searchValue.add(dto);
                }
                return searchValue;
            }
        }finally {
            closeConnection();
        }
        return null;
    }

    /*
     * Udate product by Id using dbo.product table
     * */

    public boolean createNewProduct(Product product) throws SQLException, ClassNotFoundException{

        try {
            con = MyConnection.myConnection();
            if (con != null){
                String sql = "INSERT INTO dbo.Product(ProductName,CategoriesId,ImageSrc) VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getProductName());
                stm.setInt(2, product.getCategoriesId());
                stm.setString(3, product.getImageSrc());
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


    /*
     * Create new product using dbo.product
     * */

    public boolean updateProById(int proId, String proName, String pic, int cate) throws SQLException, ClassNotFoundException{

        try {
            con = MyConnection.myConnection();
            if (con != null){
                String sql =
                        "UPDATE dbo.Product " +
                                "SET ProductName = ?, CategoriesId = ?, ImageSrc = ?" +
                                "WHERE ProductId = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, proName);
                stm.setInt(2, cate);
                stm.setString(3, pic);
                stm.setInt(4, proId);
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
