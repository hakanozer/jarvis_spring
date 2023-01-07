package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Info {

    public Info () {

    }

    public Info (String url, String username, String details, String roles) {
        this.url = url;
        this.username = username;
        this.details = details;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String username;
    private String details;
    private String roles;
    private Date date = new Date();

}
