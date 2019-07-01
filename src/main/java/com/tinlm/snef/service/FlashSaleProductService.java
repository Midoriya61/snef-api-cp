package com.tinlm.snef.service;

import com.tinlm.snef.model.FlashSaleProduct;
import com.tinlm.snef.repository.FlashSaleProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM Create getTopFlashSaleProduct
// 6/17/2019 TinLM Create getFSPByStoreId
// 6/23/2019 TinLM Create getAllFSP
@RestController
public class FlashSaleProductService {


    FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();


    // 6/17/2019 TinLM Create getTopFlashSaleProduct
    @RequestMapping(method = RequestMethod.GET, path = "/flashSaleProduct/getHotFlashSaleProduct", produces = "application/json")
    public List<FlashSaleProduct> getTopFlashSaleProduct() throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = flashSaleProductDAO.getTopFlashSaleProduct();
        return result;
    }

    // 6/17/2019 TinLM Create getFSPByStoreId
    @RequestMapping(method = RequestMethod.GET, value = "/flashSaleProduct/getFSPByStoreId/{storeId}", produces = "application/json")
    public List<FlashSaleProduct> getFSPByStoreId(@PathVariable("storeId") int storeId) throws SQLException, ClassNotFoundException{
        List<FlashSaleProduct> result = flashSaleProductDAO.getFSPByStoreId(storeId);        ;
        return result;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/flashSaleProduct/getAllFSP", produces = "application/json")
    public List<FlashSaleProduct> getAllFSP() throws SQLException, ClassNotFoundException {
        List<FlashSaleProduct> result = flashSaleProductDAO.getAllFSP();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flashSaleProduct/getFSPByCategoryId/{categoryId}", produces = "application/json")
    public List<FlashSaleProduct> getFSPByCategoriesId(@PathVariable("categoryId") int categoryId) throws SQLException, ClassNotFoundException{
        List<FlashSaleProduct> result = flashSaleProductDAO.getFSPByCategoriesId(categoryId);        ;
        return result;
    }
}
