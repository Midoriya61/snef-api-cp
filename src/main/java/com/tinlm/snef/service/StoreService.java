package com.tinlm.snef.service;

import com.tinlm.snef.model.Store;
import com.tinlm.snef.repository.StoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getAllStores
@RestController
@RequestMapping(path = "/store")
public class StoreService {
    @Autowired
    StoreDAO storeDAO = new StoreDAO();

    // 6/17/2019 TinLM Create getAllStores
    @RequestMapping(path = "/{latitude}/{longitude}" +
            "",method = RequestMethod.GET, produces = "application/json")
    public List<Store> getAllStores(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longtide) throws SQLException, ClassNotFoundException {
        List<Store> getList = storeDAO.getAllStore(latitude, longtide);
        return getList;
    }

    @RequestMapping(path = "getStoreByDistance/{latitude}/{longitude}/{distance}" +
            "",method = RequestMethod.GET, produces = "application/json")
    public List<Store> getStoreByDistance(@PathVariable("latitude") double latitude,
                                          @PathVariable("longitude") double longtide,
                                          @PathVariable("distance") double distance) throws SQLException, ClassNotFoundException {
        List<Store> getList = storeDAO.getStoreByDistance(latitude, longtide, distance);
        return getList;
    }
}
