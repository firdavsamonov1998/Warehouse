package com.company.warehouse.entity;

import com.company.warehouse.template.AbsEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Client extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String phone;
}
