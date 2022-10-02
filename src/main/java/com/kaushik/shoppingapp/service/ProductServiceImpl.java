package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.model.ProductResponseModel;
import com.kaushik.shoppingapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kaushik.shoppingapp.service.AdminProductServiceImpl.getProductResponseModel;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProductResponseModel getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> pageProducts = productRepository.findAll(pageable);
        List<Product> products = pageProducts.getContent();
        List<ProductModel> productModels = products.stream()
                .map(product -> modelMapper.map(product, ProductModel.class)).collect(Collectors.toList());
        return getProductResponseModel(pageProducts, productModels);
    }

    @Override
    public ProductModel getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        return modelMapper.map(product, ProductModel.class);
    }

    @Override
    public ProductResponseModel searchProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String keyword){
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> pageProducts = productRepository.findByNameContaining(keyword, pageable);
        List<Product> products = pageProducts.getContent();
        List<ProductModel> productModels = products.stream().map(product -> modelMapper.map(product, ProductModel.class)).collect(Collectors.toList());
        return getProductResponseModel(pageProducts, productModels);
    }

}
