package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Product {

    @Min(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long pid;

    @Length(min = 2, max = 100)
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 100)
    private String title;

    @PositiveOrZero
    @Range(min = 1, max = 100000)
    @Max(100000)
    @Min(1)
    @NotNull
    private Integer price;

}
