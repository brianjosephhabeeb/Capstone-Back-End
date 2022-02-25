package com.example.liquorapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.liquorapi.model.Bottle;
import java.util.List;

public interface BottleRepository extends JpaRepository<Bottle, Long> {
    Bottle findByName(String bottleName);
    List<Bottle> findByDistributorId(Long distributorId);
}
