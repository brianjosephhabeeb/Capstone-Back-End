package com.example.liquorapi.model;
import javax.persistence.*;

@Entity
@Table(name = "distributor")
public class Distributor {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String distributorName;

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

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", distributorName='" + distributorName + '\'' +
                '}';
    }
}
