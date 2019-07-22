package com.tinlm.snef.service;

import com.tinlm.snef.model.Order;
import com.tinlm.snef.model.Store;
import com.tinlm.snef.repository.OrderDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "order/")
public class OrderService {

    OrderDAO orderDAO = new OrderDAO();

    @RequestMapping(method = RequestMethod.GET, path = "insertNewOrder/{confirmationCode}/{accountId}", produces = "application/json")
    public boolean insertNewLikes(@PathVariable("confirmationCode") String confirmationCode, @PathVariable("accountId") int accountId) throws SQLException, ClassNotFoundException {
        boolean rs = orderDAO.createOrder(confirmationCode, accountId);
        if (rs) {
            return true;
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getLastOrder/", produces = "application/json")
    public Order getLastOrder() throws SQLException, ClassNotFoundException {
        Order result = orderDAO.getLastOrder();
        return result;
    }
}
