package com.example.liquorapi.model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "distributor")
public class Distributor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String distributorName;

    @OneToMany(mappedBy = "distributor", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bottle> bottleList;

    public Distributor(Long id, String distributorName) {
        this.id = id;
        this.distributorName = distributorName;
    }

    public Distributor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public List<Bottle> getBottleList() {
        return bottleList;
    }

    public void setBottleList(List<Bottle> bottleList) {
        this.bottleList = bottleList;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", distributorName='" + distributorName + '\'' +
                '}';
    }
}
