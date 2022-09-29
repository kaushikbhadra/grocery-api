package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.model.ProductResponseModel;

import java.util.List;

public interface AdminProductService {
    ProductModel createProduct(ProductModel productModel, Long userId);

    boolean deleteProduct(Long id);

    ProductModel updateProduct(Long id, ProductModel productModel);

    ProductResponseModel getProductByUser(Integer pageNumber,Integer pageSize,String sortBy, String sortDir, Long userId);
}
