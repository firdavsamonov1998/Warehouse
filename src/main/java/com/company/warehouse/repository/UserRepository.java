package com.company.warehouse.repository;

import com.company.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhone(String phone);

    boolean existsByPassword(String password);

    boolean existsByCode(String code);
}
