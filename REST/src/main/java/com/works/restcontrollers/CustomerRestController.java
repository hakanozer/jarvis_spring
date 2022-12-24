package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerDetailService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Customer customer) {
        return service.register(customer);
    }

}
