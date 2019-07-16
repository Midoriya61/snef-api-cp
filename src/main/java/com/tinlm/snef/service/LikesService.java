package com.tinlm.snef.service;

import com.tinlm.snef.model.Likes;
import com.tinlm.snef.repository.LikesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "likes/")
public class LikesService {

    private LikesDAO lkDao = new LikesDAO();

    @RequestMapping(method = RequestMethod.GET, path = "getById/{customerId}/{storeProductId}", produces = "application/json")
    public Likes getLikeById(@PathVariable("customerId") int customerId, @PathVariable("storeProductId") int storeProductId ) {
        Likes likes = lkDao.getLikeById(customerId, storeProductId);
        return likes;
    }

    //Get Like Product By Product Id
    @RequestMapping(method = RequestMethod.GET, path = "{product}", produces = "application/json")
    public List<Likes> getLikeByProId(@PathVariable("product") int product) throws SQLException, ClassNotFoundException {
        List<Likes> result = lkDao.getLikeByProductId(product);
        return result;
    }

    //Delete Like of StoreProduct depends on Customer
    @RequestMapping(method = RequestMethod.DELETE, path = "Delete/", produces = "application/json")
    public @ResponseBody boolean  deleteByProId(@RequestBody int proId, int cusId) throws SQLException, ClassNotFoundException {
        boolean rs = lkDao.deleteLKByProId(proId, cusId);
        if (rs){
            return true;
        }
        return false;
    }

    //Insert new Like for Store Product
//    @RequestMapping( path = "Insert/{customerId}/{storeProductId}",headers = "Content-type=application/*", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody boolean insertNewLikes(@PathVariable("customerId") int customerId, @PathVariable("storeProductId") int storeProductId) throws SQLException, ClassNotFoundException {
//        boolean rs = lkDao.insertLikeByProId(customerId, storeProductId);
//        if (rs){
//            return  true;
//        }
//        return false;
//    }
    @RequestMapping( method = RequestMethod.GET, path = "insertNewLikes/{customerId}/{storeProductId}", produces = "application/json")
    public boolean  insertNewLikes(@PathVariable("customerId") int customerId,@PathVariable("storeProductId") int storeProductId) throws SQLException, ClassNotFoundException {
        boolean rs = lkDao.insertLikeByProId(customerId, storeProductId);
        if (rs){
            return  true;
        }
        return false;
    }

    @RequestMapping( method = RequestMethod.GET, path = "deleteLike/{likeId}", produces = "application/json")
    public boolean  deleteLikes(@PathVariable("likeId") int likeId) throws SQLException, ClassNotFoundException {
        boolean rs = lkDao.deleteLikeById(likeId);
        if (rs){
            return  true;
        }
        return false;
    }
}
