package com.example.liquorapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.liquorapi.model.Distributor;

import java.util.List;

public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    Distributor findByDistributorName(String distributorName);
}
