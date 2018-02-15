package com.erikalves.application.controllers;


import com.erikalves.application.model.Product;
import com.erikalves.application.service.ProductServiceImpl;
import com.erikalves.application.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
class ProductRestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestApiController.class);

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping(value = "/exclude", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> findAllExclude() {

        LOGGER.debug("****************  Return all products (excluding relationships) **********************  ");

        List <Product> products =productService.findAllExcludingRelationships();

        if(null!=products && products.size() >0) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/include", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> findAllInclude() {

        List <Product> products = productService.findAllProductsIncludingRelationships();

        if(null!=products && products.size() >0) {
           return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping(value = "/exclude/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product>  getExclude(@PathVariable("product_id") String productId) {

        Product product =productService.findProductExcludingRelationships(Util.LongfyId(productId));
        LOGGER.debug("*** product {}",product);
        if(null!=product) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping(value = "/include/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getInclude(@PathVariable("product_id") String productId) {

        List<Product> products = productService.findProductIncludingRelationships(Util.LongfyId(productId));

        if(null!=products && products.size() >0) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //delete
    @DeleteMapping(value = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("product_id") String productId) {

        productService.delete(Util.LongfyId(productId));

        return ResponseEntity.ok((productId));
    }

    // create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody Product product) {

        Product savedProduct = productService.save(product);

        return ResponseEntity.created(URI.create("/" + product.getProductId())).body((savedProduct));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Product product) {

        productService.update(product);

        return ResponseEntity.noContent().build();
    }

}