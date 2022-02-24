package com.example.liquorapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.liquorapi.model.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    Distributor findByName(String distributorName);
}
