package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAllProducts();

    ProductModel getProductById(Long id);
}
