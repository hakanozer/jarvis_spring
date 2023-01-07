package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Note extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    @Column(unique = true)
    private String title;
    private String detail;

}
