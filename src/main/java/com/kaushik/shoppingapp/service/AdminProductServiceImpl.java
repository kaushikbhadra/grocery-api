package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.ProductModel;

import com.kaushik.shoppingapp.repository.ProductRepository;
import com.kaushik.shoppingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminProductServiceImpl implements AdminProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductModel createProduct(ProductModel productModel, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Product product = new Product();
        BeanUtils.copyProperties(productModel,product);
        product.setImageName("default.png");
        product.setCreateAt(new Date());
        product.setUser(user);
        Product prod =  productRepository.save(product);
        return modelMapper.map(prod, ProductModel.class);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        productRepository.delete(product);
        return true;
    }

    @Override
    public Product updateProduct(Long id, ProductModel productModel) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setPrice(productModel.getPrice());
        product.setCategory(productModel.getCategory());
        product.setStock(productModel.getStock());
        product.setRatings(productModel.getRatings());
        product.setNumberOfReviews(productModel.getNumberOfReviews());
        product.setImageName(productModel.getImageName());
        productRepository.save(product);
        return product;
    }

    @Override
    public List<ProductModel> getProductByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
            List<Product> products = productRepository.findProductByUser(user);
            List<ProductModel> productModels = products.stream().map(product -> modelMapper.map(product, ProductModel.class)).collect(Collectors.toList());
            return productModels;
    }
}
