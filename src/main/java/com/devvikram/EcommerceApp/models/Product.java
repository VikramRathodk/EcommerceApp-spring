package com.devvikram.EcommerceApp.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private String productDescription;
    private int productQuantity;
    private String category;

    private String releaseDate;
    private boolean isAvailable;

    private String imageType;
    @Lob
    private byte[] imageData;
    private String imageName;

    public Product() {
    }

    public Product(int productId, String productName, BigDecimal productPrice,
                   String productDescription, int productQuantity, String category,
                   String releaseDate, boolean isAvailable, String imageType, byte[] imageData, String imageName) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.category = category;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
        this.imageType = imageType;
        this.imageData = imageData;
        this.imageName = imageName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription + '\'' +
                ", productQuantity=" + productQuantity +
                ", category='" + category + '\'' +
                ", releaseDate=" + releaseDate +
                ", isAvailable=" + isAvailable +
                ", imageType='" + imageType + '\'' +
                ", imageData='" + Arrays.toString(imageData) + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
