package com.tinlm.snef.service;

import com.tinlm.snef.model.Product;
import com.tinlm.snef.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductService {

    @RequestMapping(method = RequestMethod.GET, value = "/getListNameProduct",produces = "application/json")
    public List<Product> getListNameProduct() throws SQLException, ClassNotFoundException {
        ProductDAO proDao = new ProductDAO();
            List<Product> result = proDao.getListNameProduct();
        return result;
    }

}
