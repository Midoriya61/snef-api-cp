package com.tinlm.snef.service;

import com.tinlm.snef.model.Product;
import com.tinlm.snef.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductService {

    @Autowired
    ProductDAO proDao = new ProductDAO();

    /*
     * Get ALL Product API
     * */
    @RequestMapping(method = RequestMethod.GET, path = "/products", produces = "application/json")
    public List<Product> getAllPro() throws SQLException, ClassNotFoundException {
        List<Product> getList = proDao.loadAllProduct();
        return getList;
    }

    /*
     * Get Product Name API
     * */

    @RequestMapping(method = RequestMethod.GET, value = "/products/{name}", produces = "application/json")
    public List<Product> getProName(@PathVariable String name) throws SQLException, ClassNotFoundException{
        List<Product> getListName = proDao.searchProByName(name);        ;
        return getListName;
    }

    /**
     * Create new product in table dbo.product
     * Method Post
     * Path: /products/create
     */
    @PostMapping(value = "/products/create")
    public boolean createNewProduct(@Valid @RequestBody Product product) throws SQLException, ClassNotFoundException {
        String proName = product.getProductName();
        String des = product.getDescription();
        String pic = product.getPicture();
        int cate = product.getCategoriesId();

        boolean result = proDao.createNewProduct(new Product(proName, des, pic, cate));
        System.out.println(result);

        return true;
    }

    /**
     * Update product by Id in table dbo.product
     * Method Post, path: /products/update
     */
    @PostMapping(value = "/products/update", produces = "application/json")
    public boolean updateProById(@Valid @RequestBody Product product) throws SQLException, ClassNotFoundException {
        int proId = product.getProductId();
        String proName = product.getProductName();
        String des = product.getDescription();
        String pic = product.getPicture();
        int cate = product.getCategoriesId();

        boolean rs = proDao.updateProById(proId,proName,des, pic, cate);

        return true;
    }


}
