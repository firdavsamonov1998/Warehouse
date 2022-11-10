package com.company.warehouse.controller;

import com.company.warehouse.entity.Product;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.ProductDTO;
import com.company.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")//this method adds new product data in DB
    private Message add(@RequestBody ProductDTO productDTO) {
        return productService.add(productDTO);
    }


    @GetMapping("/get") //this method  returns all the product data
    private List<Product> getAll() {
        return productService.getAll();
    }


    @GetMapping("/get/{id}") //this method returns the product data by id
    private Product getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PutMapping("/edite/{id}") //this method edites the product data in DB
    private Message edite(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productService.edite(id, productDTO);
    }

    @DeleteMapping("/delete/{id}") //this method deletes the product data in DB
    private Message delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

}
