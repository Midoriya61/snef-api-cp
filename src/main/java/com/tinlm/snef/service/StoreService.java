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
public class StoreService {
    @Autowired
    StoreDAO storeDAO = new StoreDAO();

    // 6/17/2019 TinLM Create getAllStores
    @RequestMapping(method = RequestMethod.GET, path = "/store", produces = "application/json")
    public List<Store> getAllStores() throws SQLException, ClassNotFoundException {
        List<Store> getList = storeDAO.getAllStore();
        return getList;
    }

    // TinLM get store by id
    @RequestMapping(method = RequestMethod.GET, path = "/store/getById/{storeId}", produces = "application/json")
    public Store getStoreById(@PathVariable int storeId) throws SQLException, ClassNotFoundException {
        Store result = storeDAO.getStoreById(storeId);
        return result;
    }
}
