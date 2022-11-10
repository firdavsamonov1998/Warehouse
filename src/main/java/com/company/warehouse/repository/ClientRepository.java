package com.company.warehouse.repository;

import com.company.warehouse.entity.Client;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhone(String phone);
}
