package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;

    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            repository.save(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product);
            return new ResponseEntity(hm, HttpStatus.OK );
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            if ( ex.getMessage().contains("ConstraintViolationException") ) {
                hm.put(REnum.message, "Title Unique");
            }
            hm.put(REnum.result, product);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST );
        }
    }


    public ResponseEntity update(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Product> optionalProduct = repository.findById(product.getPid());
        if (optionalProduct.isPresent()) {
            repository.saveAndFlush(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product );
            return new ResponseEntity(hm, HttpStatus.OK);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Product Not found");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity delete( String stPid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            long pid = Long.parseLong(stPid);
            repository.deleteById(pid);
            hm.put(REnum.status, true);
            hm.put(REnum.message, "Delete Success" );
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception Ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Delete Error (ID) :" + stPid  );
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

}
