package com.company.warehouse.repository;

import com.company.warehouse.entity.Attachment;
import com.company.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCode(String code);

    boolean existsByName(String name);

    @Query(value = "select count(a.id) from attachment as a inner join product_attachment as pa on a.id = pa.attachment_id " +
            "inner join product as p on p.id = pa.product_id where a.id=?1", nativeQuery = true)
    Integer countByAttachmentid(Integer id);
}
