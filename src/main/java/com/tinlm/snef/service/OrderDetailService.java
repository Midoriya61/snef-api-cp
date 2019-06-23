package com.tinlm.snef.service;


import com.tinlm.snef.repository.OrderDetailDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class OrderDetailService {

    @RequestMapping(method = RequestMethod.GET, value = "/orderDetail/getQuantityByFSPId/{flashsaleProductId}", produces = "application/json")
    public int getProName(@PathVariable("flashsaleProductId") int flashsaleProductId) throws SQLException, ClassNotFoundException{
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        int result = orderDetailDAO.getQuantityByFSPId(flashsaleProductId);        ;
        return result;
    }
}
