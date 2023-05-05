package com.example.week7springmvc.DTO;

import com.example.week7springmvc.Model.Cart;
import com.example.week7springmvc.Model.Product;
import com.example.week7springmvc.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private  Integer  quantity;
    private User user;

    private Product product;


}
