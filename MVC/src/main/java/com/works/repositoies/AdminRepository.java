package com.works.repositoies;

import com.works.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmailEqualsIgnoreCase(String email);

    // select * from admin where email = ?
    // Optional<Admin> findByEmailEqualsIgnoreCase(String email);

}