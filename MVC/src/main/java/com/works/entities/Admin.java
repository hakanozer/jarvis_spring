package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(length = 100)
    private String name;

    @Email
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 100)
    private String email;

    @NotEmpty
    @NotNull
    @Column(length = 150)
    private String password;

}
