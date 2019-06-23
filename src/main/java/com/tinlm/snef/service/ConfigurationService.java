package com.tinlm.snef.service;

// 6/22/2019 TinLM Create class

import com.tinlm.snef.repository.ConfigurationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationService {


    @RequestMapping(method = RequestMethod.GET, path = "configuration/getValueById/{configurationName}", produces = "application/json")
    public String getValueByName(@PathVariable String configurationName) {
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        String result = configurationDAO.getValueByName(configurationName);
        return result;
    }

}
