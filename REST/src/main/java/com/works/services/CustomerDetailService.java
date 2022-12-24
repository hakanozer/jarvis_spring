package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerDetailService implements UserDetailsService {

    final PasswordEncoder passwordEncoder;
    final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity register(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            customer.setPassword( passwordEncoder.encode(customer.getPassword()) );
            customerRepository.save(customer);
            hm.put(REnum.status, true);
            hm.put(REnum.result, customer);
            return new ResponseEntity(hm, HttpStatus.OK );
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            if ( ex.getMessage().contains("ConstraintViolationException") ) {
                hm.put(REnum.message, "Username Unique");
            }
            hm.put(REnum.result, customer);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST );
        }
    }

}
