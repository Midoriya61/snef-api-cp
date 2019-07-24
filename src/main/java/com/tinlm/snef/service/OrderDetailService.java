package com.tinlm.snef.service;


import com.tinlm.snef.model.Order;
import com.tinlm.snef.model.OrderDetail;
import com.tinlm.snef.repository.OrderDAO;
import com.tinlm.snef.repository.OrderDetailDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping( path = "/orderDetail")
public class OrderDetailService {

    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @RequestMapping(method = RequestMethod.GET, value = "/getQuantityByFSPId/{flashsaleProductId}", produces = "application/json")
    public int getProName(@PathVariable("flashsaleProductId") int flashsaleProductId) throws SQLException, ClassNotFoundException{
        int result = orderDetailDAO.getQuantityByFSPId(flashsaleProductId);        ;
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertNewOrderDetail/{orderId}/{fspId}/{quantity}/{orderDetailPrice}", produces = "application/json")
    public boolean  insertNewOrderDetail(@PathVariable("orderId") int orderId, @PathVariable("fspId") int fspId, @PathVariable("quantity") int quantity, @PathVariable("orderDetailPrice") float orderDetailPrice) throws SQLException, ClassNotFoundException{
        boolean rs = orderDetailDAO.createOrderDetail(orderId,fspId,quantity,orderDetailPrice);
        if (rs){
            return  true;
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAllOrderDetailByOrderId/{orderId}", produces = "application/json")
    public List<OrderDetail> getAllOrderDetailByOrderId(@PathVariable int orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetail> getList = orderDetailDAO.getAllOrderDetailByOrderId(orderId);
        return getList;
    }
}
