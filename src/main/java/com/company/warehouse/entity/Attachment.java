package com.company.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fileOriginalName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;
}
