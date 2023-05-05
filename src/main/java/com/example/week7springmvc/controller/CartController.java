package com.example.week7springmvc.controller;

import com.example.week7springmvc.DTO.CartDTO;
import com.example.week7springmvc.Model.Cart;
import com.example.week7springmvc.Services.CartService;
import com.example.week7springmvc.Services.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

   @GetMapping("/cart")
    public ModelAndView cart(HttpSession session){
       Long userid = (Long) session.getAttribute("user-number");
       ModelAndView model = new ModelAndView();
       model.addObject("cart", new CartDTO());
       System.out.println(cartService.getProductById(userid));
       model.addObject("list",cartService.getProductById(userid));
       model.setViewName("User/cart");
       return model;
   }
   @PostMapping("/cart")
    public ModelAndView newCart(@RequestParam("userid")Long userid,@RequestParam("productId") Long productId, ModelAndView modelAndView){
       Cart cart =cartService.addCart(userid,productId);
       if (cart!=null){
           modelAndView.setViewName("redirect:/cart");
       }else
       modelAndView.setViewName("redirect:/shop");

           return modelAndView;
   }

}
