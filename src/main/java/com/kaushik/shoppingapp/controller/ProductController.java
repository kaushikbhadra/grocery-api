package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long id){
        ProductModel productModel= productService.getProductById(id);
        return ResponseEntity.ok(productModel);
    }
}
