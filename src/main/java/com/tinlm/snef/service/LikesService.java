package com.tinlm.snef.service;

import com.tinlm.snef.model.Likes;
import com.tinlm.snef.repository.LikesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class LikesService {
    @Autowired
    LikesDAO lkDao = new LikesDAO();

    //Get Like Product By Product Id
    @RequestMapping(method = RequestMethod.GET, path = "Likes/{product}", produces = "application/json")
    public List<Likes> getLikeByProId(@PathVariable("product") int product) throws SQLException, ClassNotFoundException {
        List<Likes> result = lkDao.getLikeByProductId(product);
        return result;
    }

    //Delete Like of StoreProduct depends on Customer
    @RequestMapping(method = RequestMethod.DELETE, path = "Likes/Delete", produces = "application/json")
    public @ResponseBody boolean  deleteByProId(@RequestBody int proId, int cusId) throws SQLException, ClassNotFoundException {
        boolean rs = lkDao.deleteLKByProId(proId, cusId);
        if (rs){
            return true;
        }
        return false;
    }

    //Insert new Like for Store Product
    @RequestMapping(method = RequestMethod.PUT, path = "Likes/Insert", produces = "application/json")
    public @ResponseBody boolean insertNewLikes(@RequestBody int cusId, int storeId) throws SQLException, ClassNotFoundException {
        boolean rs = lkDao.insertLikeByProId(storeId, cusId);
        if (rs){
            return  true;
        }
        return false;
    }
}
