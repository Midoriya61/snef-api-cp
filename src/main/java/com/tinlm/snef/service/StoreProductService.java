package com.tinlm.snef.service;

import com.tinlm.snef.model.Product;
import com.tinlm.snef.repository.StoreProductDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StoreProductService {


    @RequestMapping(method = RequestMethod.GET, value = "/storeProduct/getQuantityById/{storeProductId}", produces = "application/json")
    public int getProName(@PathVariable("storeProductId") int storeProductId) throws SQLException, ClassNotFoundException{
        StoreProductDAO storeProductDAO = new StoreProductDAO();
        int result = storeProductDAO.getQuantityById(storeProductId);        ;
        return result;
    }
}
