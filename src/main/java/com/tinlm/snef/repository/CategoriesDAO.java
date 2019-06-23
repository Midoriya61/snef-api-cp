//package com.tinlm.snef.repository;
//
//
//import java.util.List;
//import com.tinlm.snef.connection.MyConnection;
//import com.tinlm.snef.model.Categories;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//
//// 6/17/2019 TinLM Create class
//// 6/17/2019 TinLM Create getAllCategories
//public class CategoriesDAO {
//    private Connection con;
//    private PreparedStatement stm;
//    private ResultSet rs;
//
//    // 6/17/2019 TinLM Create
//    // Get all categogies
//    public List<Categories> getAllCategories() throws SQLException, ClassNotFoundException {
//        List<Categories> result = new ArrayList<>();
//        try {
//            con = MyConnection.myConnection();
//            if (con !=null){
//                String sql = "select CategoriesId, CategoryName, ImageSrc from Categories";
//                stm = con.prepareStatement(sql);
//                rs = stm.executeQuery();
//                while (rs.next()){
//                    int categoriesId = rs.getInt("CategoriesId");
//                    String categoryName = rs.getString("CategoryName");
//                    String imageSrc = rs.getString("ImageSrc");
//
//
//                    result.add(new Categories(categoriesId, categoryName, imageSrc));
//
//                }
//            }
//        }finally {
//            MyConnection.closeConnection(rs,stm, con);
//        }
//        return result;
//    }
//}
