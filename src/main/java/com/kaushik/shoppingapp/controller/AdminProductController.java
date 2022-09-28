package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("{userId}/product/create")
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel productModel, @PathVariable Long userId){
        ProductModel productModel1 = adminProductService.createProduct(productModel, userId);
        return new ResponseEntity<>(productModel1, HttpStatus.CREATED);
    }

    @GetMapping("{userId}/products")
    public ResponseEntity<List<ProductModel>> getProductByUser(@PathVariable Long userId){
        return  ResponseEntity.ok(adminProductService.getProductByUser(userId));
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
        boolean deleted = false;
        deleted = adminProductService.deleteProduct(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductModel productModel ,@PathVariable Long id){
        Product product = adminProductService.updateProduct(id, productModel);
        return ResponseEntity.ok(product);
    }
}
