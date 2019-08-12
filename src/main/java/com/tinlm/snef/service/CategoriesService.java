package com.tinlm.snef.service;

import com.google.gson.Gson;
import com.tinlm.snef.model.Categories;
import com.tinlm.snef.repository.CategoriesDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.*;
import java.util.List;

import java.sql.SQLException;
import java.util.Map;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getAllCategories
@RestController
public class CategoriesService {


    CategoriesDAO categoriesDAO = new CategoriesDAO();


    // 6/17/2019 TinLM Create getAllCategories
    @RequestMapping(method = RequestMethod.GET, path = "/categories", produces = "application/json")
    public List<Categories> getAllCategories() throws SQLException, ClassNotFoundException {
        List<Categories> getList = categoriesDAO.getAllCategories();
        return getList;
    }


}
