package com.tinlm.snef.service;

import com.tinlm.snef.model.Categories;
import com.tinlm.snef.repository.CategoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.sql.SQLException;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getAllCategories
@RestController
public class CategoriesService {

    @Autowired
    CategoriesDAO categoriesDAO = new CategoriesDAO();


    // 6/17/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/categories", produces = "application/json")
    public List<Categories> getAllCategories() throws SQLException, ClassNotFoundException {
        List<Categories> getList = categoriesDAO.getAllCategories();
        return getList;
    }
}
