package com.example.liquorapi.controller;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.repository.DistributorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/api/distributor")
public class DistributorController {

    private DistributorRepository distributorRepository;

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

}
