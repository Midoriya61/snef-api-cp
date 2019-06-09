package com.tinlm.snef.service;

import com.tinlm.snef.model.ProductFlashSales;
import com.tinlm.snef.repository.ProductFlashSalesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductFlashSalesService {

    @Autowired
    ProductFlashSalesDAO faSalesDAO = new ProductFlashSalesDAO();

    @RequestMapping(method = RequestMethod.GET, path = "/flashsales/today", produces = "application/json")
    public List<ProductFlashSales> getByToday() throws SQLException, ClassNotFoundException{
        List<ProductFlashSales> rs = faSalesDAO.loadFsToDay();
        System.out.println(rs.size());
        return rs;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flashsales/tomorrow", produces = "application/json")
    public List<ProductFlashSales> getByTomorrow() throws SQLException, ClassNotFoundException{
        List<ProductFlashSales> rs = faSalesDAO.loadFsTomoroww();
        System.out.println(rs.size());
        return rs;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flashsales/next", produces = "application/json")
    public List<ProductFlashSales> getByNext() throws SQLException, ClassNotFoundException{
        List<ProductFlashSales> rs = faSalesDAO.loadFsNext();
        System.out.println(rs.size());
        return rs;
    }


}