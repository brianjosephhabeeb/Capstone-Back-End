package com.example.liquorapi.controller;
import com.example.liquorapi.exceptions.InformationExistsException;
import com.example.liquorapi.exceptions.InformationNotFoundException;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.repository.DistributorRepository;

import com.example.liquorapi.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/api")
public class DistributorController {

private DistributorService distributorService;
    @Autowired
    public void setDistributorService(DistributorService distributorService) {
    this.distributorService = distributorService;
    }

    @GetMapping("/distributor")
    public List<Distributor> getDistributors(){
        System.out.println("getDistributors being called");
        return distributorService.getDistributors();
    }

    @GetMapping(path = "/distributor/{distributorId}")
    public Optional getDistributor(@PathVariable Long distributorId){
        System.out.println("Get individual Distributor");
        return distributorService.getDistributor(distributorId);
    }

    @PostMapping("/distributor/")
    public Distributor createDistributor(@RequestBody Distributor distributorObject) {
        System.out.println("Create distributor called.");
        return distributorService.createDistributor(distributorObject);
    }

    @PutMapping("/distributor/{distributorId}")
    public Distributor updateDistributor(@PathVariable(value = "distributorId") Long distributorId, @RequestBody Distributor distributorObject) {
        System.out.println("Update distributor called");
        return distributorService.updateDistributor(distributorId, distributorObject);
    }

    @DeleteMapping("/distributor/{distributorId}")
    public Optional<Distributor> deleteDistributor(@PathVariable(value = "distributorId") Long distributorId){
        System.out.println("Delete distributor called.");
        return distributorService.deleteDistributor(distributorId);
    }

}
