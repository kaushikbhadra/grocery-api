package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductModel> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductModel> productModels = products.stream()
                .map(product -> new ProductModel(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getCategory(),
                        product.getRatings(),
                        product.getNumberOfReviews(),
                        product.getImageName()
                )).collect(Collectors.toList());
        return productModels;
    }

    @Override
    public ProductModel getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }
}
