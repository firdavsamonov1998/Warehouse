package com.company.warehouse.entity;

import com.company.warehouse.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {

    @Column(unique = true)
    private String code;

    @OneToOne
    @ToString.Exclude
    private Attachment attachment;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Measurment measurment;
}
