package com.example.week7springmvc.DTO;

import com.example.week7springmvc.Model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ProductDTO {
    private  Long id;
    private String productName;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private String image;
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.quantity = product.getQuantity();
        this.price =  product.getPrice();
        this.image =  product.getImage();
    }
}
