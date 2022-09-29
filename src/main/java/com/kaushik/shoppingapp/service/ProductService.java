package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.model.ProductResponseModel;


public interface ProductService {
    ProductResponseModel getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    ProductModel getProductById(Long id);

    ProductResponseModel searchProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String keyword);
}
