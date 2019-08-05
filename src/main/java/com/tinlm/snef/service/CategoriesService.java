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
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "stackoverflow";
        String charset = "UTF-8";

        URL url = null;
        try {
            url = new URL(google + URLEncoder.encode(search, charset));
            Reader reader = new InputStreamReader(url.openStream(), charset);
            OpenStreetMapUtils results = new Gson().fromJson(reader, OpenStreetMapUtils.class);

            // Show title and URL of 1st result.
            System.out.println(results.getResponseData().getResults().get(0).getTitle());
            System.out.println(results.getResponseData().getResults().get(0).getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return getList;
    }


}
