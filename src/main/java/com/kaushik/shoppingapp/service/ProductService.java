package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.model.ProductResponseModel;

import java.util.List;

public interface ProductService {
    ProductResponseModel getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    ProductModel getProductById(Long id);
}
