package com.company.warehouse.repository;

import com.company.warehouse.entity.Measurment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurment, Integer> {
    boolean existsMeasurmentByName(String name);
}
