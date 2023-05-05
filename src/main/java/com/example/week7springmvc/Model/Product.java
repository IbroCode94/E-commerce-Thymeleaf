package com.example.week7springmvc.Model;

import com.example.week7springmvc.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_names")
    private String productName;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private String image;



    public Product(ProductDTO productDTO) {
        this.productName = productDTO.getProductName();
        this.category = productDTO.getCategory();
        this.quantity =productDTO.getQuantity();
        this.price =  productDTO.getPrice();
        this.image =  productDTO.getImage();
    }

    public Product() {

    }
}
