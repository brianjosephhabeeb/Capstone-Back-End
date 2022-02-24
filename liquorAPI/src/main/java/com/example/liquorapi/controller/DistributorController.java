package com.example.liquorapi.controller;
import com.example.liquorapi.exceptions.InformationExistsException;
import com.example.liquorapi.exceptions.InformationNotFoundException;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.repository.DistributorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "/api")
public class DistributorController {

    private DistributorRepository distributorRepository;

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    @GetMapping("/distributor")
    public List<Distributor> getDistributor(){
        System.out.println("getDistributor being called");
        return distributorRepository.findAll();
    }

    @GetMapping(path = "/distributor/{distributorId}")
    public Optional getDistributor(@PathVariable Long distributorId){
        System.out.println("Get individual Distributor");
        Optional distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()){
            return distributor;
        } else {
            throw new InformationNotFoundException("Distributor with the id " + distributorId + " not found.");
        }
    }

    @PostMapping("/distributor/")
    public Distributor createDistributor(@RequestBody Distributor distributorObject) {
        System.out.println("Create distributor called.");
        Distributor distributor = distributorRepository.findByName(distributorObject.getDistributorName());
        if (distributor != null) {
            throw new InformationExistsException("Distributor with the name " + distributor.getDistributorName() + " already exists in database.");
        } else {
            return distributorRepository.save(distributorObject);
        }
    }

    @PutMapping("/distributot/{distributorId}")
    public Distributor updateDistributor(@PathVariable(value = "distributorId") Long distributorId, @RequestBody Distributor distributorObject) {
        System.out.println("Update distributor called");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()){
            if (distributorObject.getDistributorName().equals(distributor.get().getDistributorName())) {
                System.out.println("Distributor with same name.");
                throw new InformationExistsException("Distributor " + distributor.get().getDistributorName() + " already exists.");
            } else {
                Distributor updateDistributor = distributorRepository.getById(distributorId).get();
                updateDistributor.setDistributorName(distributorObject.getDistributorName());
                return distributorRepository.save(updateDistributor);
            }
        } else {
            throw new InformationNotFoundException("Distributor with id " + distributorId + " not found in database.");
        }
    }

    @DeleteMapping("/distributor/{distributorId}")
    public Optional<Distributor> deleteDistributor(@PathVariable(value = "distributorId") Long distributorId){
        System.out.println("Delete distributor called.");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            distributorRepository.deleteById(distributorId);
            return distributor;
        } else {
            throw new InformationNotFoundException("Distributor with the id " + distributorId + " not found in database.");
        }
    }

}
