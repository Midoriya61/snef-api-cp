package com.tinlm.snef.service;
import com.tinlm.snef.model.FlashSaleProductImage;
import com.tinlm.snef.repository.FlashSaleProductImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getOneFlashSaleProductImageById
// 6/17/2019 TinLM Create getFlashSaleProductImageById
@RestController
public class FlashSaleProductImageService {

    @Autowired
    FlashSaleProductImageDAO flashSaleProductImageDAO = new FlashSaleProductImageDAO();

    // 6/17/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/flashSaleProductImage/getOneImage/{flashSaleProductId}", produces = "application/json")
    public FlashSaleProductImage getOneFlashSaleProductImageById(@PathVariable("flashSaleProductId") int flashSaleProductId) throws SQLException, ClassNotFoundException {

        FlashSaleProductImage searchValue =flashSaleProductImageDAO.getOneFlashSaleProductImageById(flashSaleProductId);
        return searchValue;
    }

    // 6/17/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/flashSaleProductImage/getImage{flashSaleProductId}", produces = "application/json")
    public List<FlashSaleProductImage> getFlashSaleProductImageById(@PathVariable("flashSaleProductId") int flashSaleProductId) throws SQLException, ClassNotFoundException {

        List<FlashSaleProductImage> searchValue =flashSaleProductImageDAO.getFlashSaleProductImageById(flashSaleProductId);
        return searchValue;
    }
}
