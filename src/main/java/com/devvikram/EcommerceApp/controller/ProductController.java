package com.devvikram.EcommerceApp.controller;

import com.devvikram.EcommerceApp.models.Product;
import com.devvikram.EcommerceApp.servies.ProductService;
import com.devvikram.EcommerceApp.utilities.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService  = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseResult<?>> getAllProducts(){
        try{
            String message;
            List<Product> products = productService.getAllProducts();
            if(products.isEmpty()){
                message = "No products found";
            }else{
                message = "Products retrieved successfully";
            }
            return ResponseEntity.ok().body(
                    new ResponseResult.Success<>(products, message)
            );

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ResponseResult.Error<>(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            ));
        }
    }


    // add a new product
    @PostMapping("/add-product")
    public ResponseEntity<ResponseResult<?>> addProduct(@ModelAttribute  Product product,
                                                         @RequestPart("image") MultipartFile imageFile) {
        try {

            System.out.println("new product si : "+product);
            Product existingProduct = productService.getProductByName(product.getProductName());
            System.out.println("Existing product : "+existingProduct);
            if (existingProduct != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR, "Product already exists"));
            }
            Product newProduct = productService.addProduct(product, imageFile);
            System.out.println("Newly Product "+ newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseResult.Success<>(newProduct, "Product added successfully"));

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR,
                            e.getMessage()));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseResult<?>> updateProduct(@RequestParam int productId,@ModelAttribute  Product product,
                                                         @RequestPart("image") MultipartFile imageFile) {
        try {
            Product existingProduct = productService.getProductById(productId);
            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR, "Product not found"));
            }
            Product updatedProduct = productService.updateProduct(productId,product, imageFile);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseResult.Success<>(updatedProduct, "Product updated successfully"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR,
                            e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseResult<?>> deleteProduct(@RequestParam int productId) {
        try {
            Product existingProduct = productService.getProductById(productId);
            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR, "Product not found"));
            }
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseResult.Success<>(null, "Product deleted successfully"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR,
                            e.getMessage()));
        }
    }
    @GetMapping("/get_product_image")
    public ResponseEntity<ResponseResult<?>> getProductImage(@RequestParam int productId) {
        byte[] productImage = productService.getProductImage(productId);
        if (productImage == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseResult.Error<>(HttpStatus.INTERNAL_SERVER_ERROR, "Product image not found"));
        }
        return ResponseEntity.ok().body(
                new ResponseResult.Success<>(productImage, "Product image retrieved successfully"));

    }


}
