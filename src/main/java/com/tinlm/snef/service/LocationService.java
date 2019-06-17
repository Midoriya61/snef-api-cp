package com.tinlm.snef.service;

import com.tinlm.snef.model.Location;
import com.tinlm.snef.repository.LocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

// 6/17/2019 TinLM Create class
// 6/17/2019 TinLM getById
@RestController
public class LocationService {

    @Autowired
    LocationDAO locationDAO = new LocationDAO();


    // 6/17/2019 TinLM Create
    @RequestMapping(method = RequestMethod.GET, path = "/location/{locationId}", produces = "application/json")
    public Location getById(@PathVariable("locationId") int locationId) throws SQLException, ClassNotFoundException {

        Location searchValue =locationDAO.getLocatinById(locationId);
        return searchValue;
    }

}
