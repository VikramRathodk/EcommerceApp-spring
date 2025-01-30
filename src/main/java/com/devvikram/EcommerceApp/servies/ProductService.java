package com.devvikram.EcommerceApp.servies;

import com.devvikram.EcommerceApp.models.Product;
import com.devvikram.EcommerceApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        return productRepository.save(product);
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product updateProduct(int productId,Product product, MultipartFile imageFile) throws IOException {
        Product existingProduct = getProductById(productId);
        if(existingProduct!= null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductDescription(product.getProductDescription());
            existingProduct.setProductQuantity(product.getProductQuantity());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setReleaseDate(product.getReleaseDate());
            existingProduct.setAvailable(product.isAvailable());
            if(imageFile!= null) {
                existingProduct.setImageData(imageFile.getBytes());
                existingProduct.setImageType(imageFile.getContentType());
                existingProduct.setImageName(imageFile.getOriginalFilename());
            }
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(int productId)
    {
        productRepository.deleteById(productId);
    }

    public byte[] getProductImage(int productId) {
        Product product = getProductById(productId);
        if(product!= null) {
            return product.getImageData();
        }
        return null;
    }
}
