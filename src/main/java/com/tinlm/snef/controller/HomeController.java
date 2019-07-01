package com.tinlm.snef.controller;

import com.tinlm.snef.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class HomeController {
    @Autowired
    StoreService storeService;

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/home"})
    public String home(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("LISTSTORE", storeService.getAllStores());
        return "home.jsp";
    }

}
