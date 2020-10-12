package kz.iitu.shop.controller;

import kz.iitu.shop.models.Product;
import kz.iitu.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    public ProductRepository productRepos;

    @GetMapping("/list")
    Collection<Product> getProducts() {
        return productRepos.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> product = productRepos.findById(id);
        return product.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepos.deleteById(id);
    }

}
