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

    @RequestMapping(method = RequestMethod.GET, path = "insertNewOrder/{confirmationCode}/{accountId}/{storeid}", produces = "application/json")
    public boolean insertNewLikes(@PathVariable("confirmationCode") String confirmationCode, @PathVariable("accountId") int accountId,
                                  @PathVariable("storeid") int storeId) throws SQLException, ClassNotFoundException {
        boolean rs = orderDAO.createOrder(confirmationCode, accountId, storeId);
        if (rs) {
            return true;
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getLastOrderId", produces = "application/json")
    public int getLastOrderId() throws SQLException, ClassNotFoundException {
        int lastOrderId = orderDAO.getLastOrderId();
        return lastOrderId;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAllOrder/", produces = "application/json")
    public List<Order> getAllOrder() throws SQLException, ClassNotFoundException {
        List<Order> result = orderDAO.getAllOrder();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getOrderByAccountId/{accountId}", produces = "application/json")
    public List<Order> getOrderByAccountId(@PathVariable("accountId") int accountId) throws SQLException, ClassNotFoundException {
        List<Order> result = orderDAO.getOrderByAccountId(accountId);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getOrderById/{orderId}", produces = "application/json")
    public Order getOrderById(@PathVariable("orderId") int orderId) throws SQLException, ClassNotFoundException {
        Order result = orderDAO.getOrderById(orderId);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "submitFeedback/{orderId}/{ratingPoint}/{comment}", produces = "application/json")
    public boolean submitFeedback(@PathVariable("orderId") int orderId, @PathVariable("ratingPoint") float ratingPoint,
                                  @PathVariable("comment") String comment) throws SQLException, ClassNotFoundException {
        boolean result = orderDAO.submitFeedback(orderId, ratingPoint, comment);
        return result;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "getOrderByStoreId/{storeId}", produces = "application/json")
//    public List<Order> getOrderByStoreId(@PathVariable("storeId") int storeId) throws SQLException, ClassNotFoundException {
//        List<Order> result = orderDAO.getOrderByStoreId(storeId);
//        return result;
//    }


}

