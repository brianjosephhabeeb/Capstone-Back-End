package com.example.liquorapi.service;

import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributorService {

    private DistributorRepository distributorRepository;

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    public List<Distributor> getDistributor() {
        System.out.println("getDistributor service being called");
        return distributorRepository.findAll();
    }

}
