package com.company.warehouse.repository;

import com.company.warehouse.entity.OutPut_product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputProductRepository extends JpaRepository<OutPut_product, Integer> {
}
