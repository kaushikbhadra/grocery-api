package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.model.ProductModel;
import com.kaushik.shoppingapp.model.ProductResponseModel;
import com.kaushik.shoppingapp.service.AdminProductService;
import com.kaushik.shoppingapp.service.FileService;
import com.kaushik.shoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
    @Autowired
    private AdminProductService adminProductService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ProductService productService;
    @Value("${project.image}")
    private String path;
    @PostMapping("/{userId}/product/create")
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel productModel, @PathVariable Long userId){
        ProductModel productModel1 = adminProductService.createProduct(productModel, userId);
        return new ResponseEntity<>(productModel1, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/products")
    public ResponseEntity<ProductResponseModel> getProductByUser(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
            ,@PathVariable Long userId
    ){
        return  ResponseEntity.ok(adminProductService.getProductByUser(pageNumber, pageSize, sortBy, sortDir,userId));
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
        boolean deleted = false;
        deleted = adminProductService.deleteProduct(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductModel> updateProduct(@Valid @RequestBody ProductModel productModel ,@PathVariable Long id){
        return ResponseEntity.ok(adminProductService.updateProduct(id, productModel));
    }

    @PostMapping("/product/{id}/image")
    public ResponseEntity<ProductModel> uploadImage(@RequestParam(value = "image") MultipartFile imageFile, @PathVariable Long id) throws IOException {
        ProductModel productModel = productService.getProductById(id);
        String fileName = fileService.uploadImage(path, imageFile);
        productModel.setImageName(fileName);
        return ResponseEntity.ok(adminProductService.updateProduct(id,productModel));
    }

    @GetMapping(value = "/product/{productImageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void getImage(
            @PathVariable("productImageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
