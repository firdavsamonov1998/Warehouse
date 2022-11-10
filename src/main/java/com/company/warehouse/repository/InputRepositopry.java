package com.company.warehouse.repository;

import com.company.warehouse.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepositopry extends JpaRepository<Input, Integer> {
  boolean existsByFacture(String facture);
}
