package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity list( @RequestParam(defaultValue = "0") int page ) {
        return productService.list(page);
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/delete/{stPid}")
    public ResponseEntity delete( @PathVariable String stPid ) {
        return productService.delete(stPid);
    }


}
