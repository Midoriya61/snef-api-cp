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
@RequestMapping(path = "/flashSaleProduct")
public class FlashSaleProductService {





    // 6/17/2019 TinLM Create getTopFlashSaleProduct
    @RequestMapping(method = RequestMethod.GET, path = "/getHotFlashSaleProduct", produces = "application/json")
    public List<FlashSaleProduct> getTopFlashSaleProduct() throws SQLException, ClassNotFoundException {
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        List<FlashSaleProduct> result = flashSaleProductDAO.getTopFlashSaleProduct();
        return result;
    }

    // 6/17/2019 TinLM Create getFSPByStoreId
    @RequestMapping(method = RequestMethod.GET, value = "/getFSPByStoreId/{storeId}", produces = "application/json")
    public List<FlashSaleProduct> getFSPByStoreId(@PathVariable("storeId") int storeId) throws SQLException, ClassNotFoundException{
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        List<FlashSaleProduct> result = flashSaleProductDAO.getFSPByStoreId(storeId);        ;
        return result;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getAllFSP", produces = "application/json")
    public List<FlashSaleProduct> getAllFSP() throws SQLException, ClassNotFoundException {
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        List<FlashSaleProduct> result = flashSaleProductDAO.getAllFSP();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFSPByCategoryId/{categoryId}", produces = "application/json")
    public List<FlashSaleProduct> getFSPByCategoriesId(@PathVariable("categoryId") int categoryId) throws SQLException, ClassNotFoundException{
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        List<FlashSaleProduct> result = flashSaleProductDAO.getFSPByCategoriesId(categoryId);        ;
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRemaingQuantity/{fspId}", produces = "application/json")
    public int getRemaingQuantity(@PathVariable("fspId") int fspId) throws SQLException, ClassNotFoundException{
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        int result = flashSaleProductDAO.getRemaingQuantity(fspId);        ;
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFSPByName/{searchName}", produces = "application/json")
    public List<FlashSaleProduct> getFSPByName(@PathVariable("searchName") String searchName) throws SQLException, ClassNotFoundException{
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        List<FlashSaleProduct> result = flashSaleProductDAO.getFSPByName(searchName);        ;
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFSPById/{flashSaleProductId}", produces = "application/json")
    public FlashSaleProduct getFSPById(@PathVariable("flashSaleProductId") int flashSaleProductId) throws SQLException, ClassNotFoundException{
        FlashSaleProductDAO flashSaleProductDAO = new FlashSaleProductDAO();
        FlashSaleProduct result = flashSaleProductDAO.getFSPById(flashSaleProductId);        ;
        return result;
    }
}
