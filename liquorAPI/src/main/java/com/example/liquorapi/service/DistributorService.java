package com.example.liquorapi.service;

import com.example.liquorapi.exceptions.InformationExistsException;
import com.example.liquorapi.exceptions.InformationNotFoundException;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.model.Bottle;
import com.example.liquorapi.repository.BottleRepository;
import com.example.liquorapi.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    private DistributorRepository distributorRepository;
    private BottleRepository bottleRepository;

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    @Autowired
    public void setBottleRepository(BottleRepository bottleRepository) {
        this.bottleRepository = bottleRepository;
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
        Distributor distributor = distributorRepository.findByDistributorName(distributorObject.getDistributorName());
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

    public Bottle createDistributorBottle(Long distributorId, Bottle bottleObject) {
        System.out.println("Service calling createDistributorBottle.");
        try {
            Optional distributor = distributorRepository.findById(distributorId);
            bottleObject.setDistributor((Distributor) distributor.get());
            return bottleRepository.save(bottleObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Distributor with id " + distributorId + " not found.");
        }
    }

    public List<Bottle> getDistributorBottles(Long distributorId) {
        System.out.println("Service calling getDistributorBottle.");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            return distributor.get().getBottleList();
        } else {
            throw new InformationNotFoundException("Distributor with id " + distributorId + " not found.");
        }
    }

    public Bottle getDistributorBottle(Long distributorId, Long bottleId) {
        System.out.println("Service calling getDistributorBottle.");
        Optional<Distributor> distributor = distributorRepository.findById(distributorId);
        if (distributor.isPresent()) {
            Optional<Bottle> bottle = bottleRepository.findByDistributorId(distributorId).stream().filter(
                    p -> p.getId().equals(bottleId)).findFirst();
            if (bottle.isEmpty()) {
                throw new InformationNotFoundException("Bottle with id " + bottleId + " not found.");
            } else {
                return bottle.get();
            }
        } else {
            throw new InformationNotFoundException("Distributor with id " + distributorId + " not found.");
        }
    }


    public Bottle updateDistributorBottle(Long distributorId, Long bottleId, Bottle bottleObject) {
        System.out.println("Service calling updateDistributorBottle.");
        try {
            Bottle bottle = (bottleRepository.findByDistributorId(
                    distributorId).stream().filter(p -> p.getId().equals(bottleId)).findFirst()).get();
            bottle.setName(bottleObject.getName());
            bottle.setStartingInventory(bottleObject.getStartingInventory());
            bottle.setBottlesReceived(bottleObject.getBottlesReceived());
            bottle.setEndingInventory(bottleObject.getEndingInventory());
            return bottleRepository.save(bottle);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Bottle or distributor not found.");
        }
    }

    public void deleteDistributorBottle(Long distributorId, Long bottleId) {
        try {
            Bottle bottle = (bottleRepository.findByDistributorId(
                    distributorId).stream().filter(p -> p.getId().equals(bottleId)).findFirst()).get();
            bottleRepository.deleteById(bottle.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Bottle or distributor not found.");
        }
    }
}
