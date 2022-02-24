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
    private int startingInventory;

    @Column
    private int bottlesReceived;

    @Column
    private int endingInventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

//Constructor for Bottle table
//    public Bottle(Long id, String name, int startingInventory, int bottlesReceived, int endingInventory) {
//        this.id = id;
//        this.name = name;
//        this.startingInventory = startingInventory;
//        this.bottlesReceived = bottlesReceived;
//        this.endingInventory = endingInventory;
//    }

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

    public int getStartingInventory() {
        return startingInventory;
    }

    public void setStartingInventory(int startingInventory) {
        this.startingInventory = startingInventory;
    }

    public int getBottlesReceived() {
        return bottlesReceived;
    }

    public void setBottlesReceived(int bottlesReceived) {
        this.bottlesReceived = bottlesReceived;
    }

    public int getEndingInventory() {
        return endingInventory;
    }

    public void setEndingInventory(int endingInventory) {
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
