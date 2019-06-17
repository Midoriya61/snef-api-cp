package com.tinlm.snef.service;

import com.tinlm.snef.model.FlashSaleProduct;
import com.tinlm.snef.repository.FlashSaleProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create
@RestController
public class FlashSaleProductService {

    @Autowired
    FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();


    // 6/17/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/flashSaleProduct/getHotFlashSaleProduct", produces = "application/json")
    public List<FlashSaleProduct> getAllCategories() throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> getList = flashSaleProductDAO.getTopFlashSaleProduct();
        return getList;
    }
}
