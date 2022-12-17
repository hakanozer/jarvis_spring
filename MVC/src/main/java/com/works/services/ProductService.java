package com.works.services;

import com.works.props.Product;
import com.works.props.ProductData;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final RestTemplate restTemplate;

    @Cacheable("allProduct")
    public List<Product> allProduct() {
        String url = "https://dummyjson.com/products";
        ProductData data = restTemplate.getForObject(url, ProductData.class);
        return data.getProducts();
    }

}
