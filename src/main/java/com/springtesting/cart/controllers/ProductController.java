package com.springtesting.cart.controllers;

import com.springtesting.cart.models.Product;
import com.springtesting.cart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product.getId(),product.getName(), product.getDescription());
    }

}
