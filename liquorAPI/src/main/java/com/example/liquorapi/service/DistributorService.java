package com.example.liquorapi.service;

import com.example.liquorapi.exceptions.InformationExistsException;
import com.example.liquorapi.exceptions.InformationNotFoundException;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    private DistributorRepository distributorRepository;

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    public List<Distributor> getDistributors() {
        System.out.println("getDistributors service being called for all distributors");
        return distributorRepository.findAll();
    }

    public Optional getDistributor(Long distributorId) {
        System.out.println("getDistributor service called for one distributor.");
        Optional distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            return distributor;
        } else {
            throw new InformationNotFoundException("Distributor with the id " + distributorId + " not found in database.");
        }
    }

    public Distributor createDistributor(Distributor distributorObject) {
        System.out.println("service calling createDistributor.");
        Distributor distributor = distributorRepository.findByName(distributorObject.getDistributorName());
        if (distributor != null) {
            throw new InformationExistsException("Distributor with the name of " + distributor.getDistributorName() + " already exists.");
        } else {
            return distributorRepository.save(distributorObject);
        }
    }

    public Distributor updateDistributor(Long distributorId, Distributor distributorObject) {
        System.out.println("service calling updateDistributor.");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            if (distributorObject.getDistributorName().equals(distributor.get().getDistributorName())) {
                throw new InformationExistsException("distributor " + distributor.get().getDistributorName() + " already exists.");
            } else {
                Distributor updateDistributor = distributorRepository.findById(distributorId).get();
                updateDistributor.setDistributorName(distributorObject.getDistributorName());
                return distributorRepository.save(updateDistributor);
            }
        } else {
            throw new InformationNotFoundException("Distributor with the id " + distributorId + " not found.");
        }
    }

    public Optional<Distributor> deleteDistributor(Long distributorId) {
        System.out.println("service calling deleteDistributor");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            distributorRepository.deleteById(distributorId);
            return distributor;
        } else {
            throw new InformationNotFoundException("Distributor with the id " + distributorId + " not found.");
        }
    }



}
