package com.tinlm.snef.service;

import com.tinlm.snef.repository.OrderDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "order/")
public class OrderService {

    @RequestMapping( method = RequestMethod.GET, path = "insertNewOrder/{confirmationCode}/{customerId}", produces = "application/json")
    public boolean  insertNewLikes(@PathVariable("confirmationCode") String confirmationCode, @PathVariable("customerId") int customerId) throws SQLException, ClassNotFoundException {
        OrderDAO orderDAO = new OrderDAO();
        boolean rs = orderDAO.createOrder(confirmationCode, customerId);
        if (rs){
            return  true;
        }
        return false;
    }
}
