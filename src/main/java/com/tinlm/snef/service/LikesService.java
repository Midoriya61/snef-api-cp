package com.tinlm.snef.service;

import com.tinlm.snef.model.Likes;
import com.tinlm.snef.repository.LikesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class LikesService {
    @Autowired
    LikesDAO lkDao = new LikesDAO();

    @RequestMapping(method = RequestMethod.GET, path = "Likes/{product}", produces = "application/json")
    public List<Likes> getLikeByProId(@PathVariable("product") int product) throws SQLException, ClassNotFoundException {
        List<Likes> result = lkDao.getLikeByProductId(product);
        return result;
    }
}
