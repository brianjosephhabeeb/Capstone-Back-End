package com.example.liquorapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bottle")
//Building out the table
public class Bottle {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Primary Key
    private Long id;

    @Column
    private String name;

    @Column
    private Integer startingInventory;

    @Column
    private Integer bottlesReceived;

    @Column
    private Integer endingInventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

//Constructor for Bottle table
    public Bottle(Long id, String name, Integer startingInventory, Integer bottlesReceived, Integer endingInventory) {
        this.id = id;
        this.name = name;
        this.startingInventory = startingInventory;
        this.bottlesReceived = bottlesReceived;
        this.endingInventory = endingInventory;
    }

    public Bottle() {
    }
//getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartingInventory() {
        return startingInventory;
    }

    public void setStartingInventory(Integer startingInventory) {
        this.startingInventory = startingInventory;
    }

    public Integer getBottlesReceived() {
        return bottlesReceived;
    }

    public void setBottlesReceived(Integer bottlesReceived) {
        this.bottlesReceived = bottlesReceived;
    }

    public Integer getEndingInventory() {
        return endingInventory;
    }

    public void setEndingInventory(Integer endingInventory) {
        this.endingInventory = endingInventory;
    }

    public Distributor getDistributor(){
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startingInventory=" + startingInventory +
                ", bottlesReceived=" + bottlesReceived +
                ", endingInventory=" + endingInventory +
                '}';
    }


}
