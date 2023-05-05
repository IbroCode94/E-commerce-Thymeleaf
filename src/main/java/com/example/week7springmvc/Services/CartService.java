package com.example.week7springmvc.Services;

import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Model.Cart;

import java.util.List;

public interface CartService {
    Cart  addCart(Long userid,Long productId);
    List<ProductDTO> getProductById(Long id);
}
