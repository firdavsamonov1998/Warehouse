package com.company.warehouse.entity;

import com.company.warehouse.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbsEntity {
    @Column(unique = true,nullable = false)
    private String phone;
}
