package com.tinlm.snef.service;

import com.tinlm.snef.model.Account;
import com.tinlm.snef.model.Customer;
import com.tinlm.snef.repository.CustomerDAO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer/")
public class CustomerService {

    @RequestMapping(method = RequestMethod.GET, path = "login/{username}/{password}", produces = "application/json")
    public Customer login(@PathVariable("username") String username, @PathVariable("password") String password) {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer result = customerDAO.login(username, password);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "create/{username}/{password}/{firstname}/{lastname}", produces = "application/json")
    public Boolean createCustomer(@PathVariable("username") String username, @PathVariable("password") String password,
                                   @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname  ) {
        CustomerDAO customerDAO = new CustomerDAO();
        Boolean result = customerDAO.createAccount(username, password,firstname, lastname);
        return result;
    }
}
