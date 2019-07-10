package com.tinlm.snef.service;


import com.tinlm.snef.model.StoreProductImage;
import com.tinlm.snef.repository.StoreProductImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

// 6/21/2019 TinLM Create class
// 6/21/2019 TinLM Create getOneProductImageById
// 6/21/2019 TinLM Create getProductImageById
@RestController
@RequestMapping(path = "/storeProductImage")
public class StoreProductImageService {

    @Autowired
    StoreProductImageDAO storeProductImageDAO  = new StoreProductImageDAO();

    // 6/21/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/getOneImage/{storeProductId}", produces = "application/json")
    public StoreProductImage getOneProductImageById(@PathVariable("storeProductId") int storeProductId) throws SQLException, ClassNotFoundException {

        StoreProductImage searchValue =storeProductImageDAO.getOneStoreProductImageById(storeProductId);
        return searchValue;

    }

    // 6/21/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/getImage/{storeProductId}", produces = "application/json")
    public List<StoreProductImage> getProductImageById(@PathVariable("storeProductId") int storeProductId) throws SQLException, ClassNotFoundException {

        List<StoreProductImage> searchValue =storeProductImageDAO.getStoreProductImageById(storeProductId);
        return searchValue;
    }
}
