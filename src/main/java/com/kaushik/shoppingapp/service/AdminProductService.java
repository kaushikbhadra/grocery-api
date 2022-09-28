package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.model.ProductModel;

import java.util.List;

public interface AdminProductService {
    ProductModel createProduct(ProductModel productModel, Long userId);

    boolean deleteProduct(Long id);

    Product updateProduct(Long id, ProductModel productModel);

    List<ProductModel> getProductByUser(Long userId);
}
