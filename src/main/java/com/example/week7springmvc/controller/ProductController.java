package com.example.week7springmvc.controller;

import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Services.AdminService;
import com.example.week7springmvc.Services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    private ProductService productService;
    private AdminService adminService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public ModelAndView homePageWithMV(ModelAndView modelAndView, HttpSession session) {

        if (session.getAttribute("user-number")==null){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Long id = (Long) session.getAttribute("user-number");
        modelAndView.setViewName("User/shop");
        modelAndView.addObject("product", productService.findAlls());
        modelAndView.addObject("list_product", productService.findAlls());
        modelAndView.addObject("userid",id);
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView modelAndView, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user-number")==null){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.setViewName("User/index");
        modelAndView.addObject("product", productService.findAlls());
        return modelAndView;
    }

//    @GetMapping("/add_product")
//    public String productForm(Model model, HttpServletRequest httpServletRequest){
//        HttpSession session = httpServletRequest.getSession();
//        if (session.getAttribute("user-number")==null){
//            return "index";
//        }
//        model.addAttribute("product", new ProductDTO());
//        return "add_product";
//    }
//    @PostMapping("/add_product")
//    public String productForm(@ModelAttribute("product") ProductDTO product, HttpServletRequest httpServletRequest){
//        HttpSession session = httpServletRequest.getSession();
//        if (session.getAttribute("user-number")==null){
//            return "index";
//        }
//        productService.saveProduct(product, (Long)session.getAttribute("user-number"));
//        return "home";
//    }
}
