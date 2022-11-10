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
public class Category extends AbsEntity {
    @ManyToOne
    private Category parentCategory;


}
