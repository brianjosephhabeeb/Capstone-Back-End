package com.example.liquorapi.controller;
import com.example.liquorapi.model.Distributor;
import com.example.liquorapi.model.Bottle;
import com.example.liquorapi.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
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

    @PostMapping("/distributor")
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

    @GetMapping("/bottles")
    public List<Bottle> getBottles() {
        System.out.println("getBottles being called");
        return distributorService.getBottles();
    }

    @GetMapping("/distributor/{distributorId}/bottles")
    public List<Bottle> getDistributorBottles(@PathVariable(value = "distributorId") Long distributorId) {
        System.out.println("calling getDistributorBottles.");
        return distributorService.getDistributorBottles(distributorId);
    }

    @GetMapping("/bottles/{bottleId}")
    public Optional getBottle(
            @PathVariable Long bottleId) {
        System.out.println("calling getBottle.");
        return distributorService.getBottle(bottleId);
    }

    @PostMapping("/distributor/{distributorId}/bottles")
    public Bottle createDistributorBottle(
            @PathVariable(value = "distributorId") Long distributorId, @RequestBody Bottle bottleObject) {
        System.out.println("calling createDistributorBottle.");
        return distributorService.createDistributorBottle(distributorId, bottleObject);
    }

    @PutMapping("/distributor/{distributorId}/bottles/{bottleId}")
    public Bottle updateDistributorBottle(@PathVariable(value = "distributorId") Long distributorId,
                                       @PathVariable(value = "bottleId") Long bottleId,
                                       @RequestBody Bottle bottleObject) {
        System.out.println("calling updateDistributorBottle.");
        return distributorService.updateDistributorBottle(distributorId, bottleId, bottleObject);
    }

    @DeleteMapping("/distributor/{distributorId}/bottles/{bottleId}")
    public ResponseEntity<HashMap> deleteDistributorBottle(
            @PathVariable(value = "distributorId") Long distributorId, @PathVariable(value = "bottleId") Long bottleId) {
        System.out.println("calling deleteDistributorBottle");
        distributorService.deleteDistributorBottle(distributorId, bottleId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "bottle with id: " + bottleId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

}
