package com.tinlm.snef.service;

import com.tinlm.snef.model.StoreFollower;
import com.tinlm.snef.repository.StoreFollowerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StoreFollowerService {
    @Autowired
    StoreFollowerDAO sfDao = new StoreFollowerDAO();

    @RequestMapping(method = RequestMethod.GET, name = "StoreFollower/{customer}", consumes = "application/json")
    public List<StoreFollower> getStoreByCus(@PathVariable("customer") int custId) throws SQLException, ClassNotFoundException {
        List<StoreFollower> getAll = sfDao.getFollowByCusId(custId);
        return getAll;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "StoreFollower/delete", produces = "application/json")
    public @ResponseBody boolean  deleteByProId(@RequestBody int storeId, int cusId) throws SQLException, ClassNotFoundException {
        boolean rs = sfDao.deleteFollower(storeId, cusId);
        if (rs){
            return true;
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "Likes/insert", produces = "application/json")
    public @ResponseBody boolean insertNewLikes(@RequestBody int cusId, int storeId) throws SQLException, ClassNotFoundException {
        boolean rs = sfDao.insertNewFollower(storeId, cusId);
        if (rs){
            return  true;
        }
        return false;
    }
}
