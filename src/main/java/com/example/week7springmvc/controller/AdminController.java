package com.example.week7springmvc.controller;

import com.example.week7springmvc.DTO.AdminDTO;
import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Model.Admin;
import com.example.week7springmvc.Services.AdminService;
import com.example.week7springmvc.Services.ProductService;
import com.example.week7springmvc.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private UserService userService;
    private AdminService adminService;
    private ProductService productService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService, ProductService productService) {
        this.userService = userService;
        this.adminService = adminService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/Admin/dist/index");
        modelAndView.addObject("admin", new AdminDTO());
        return modelAndView;

    }

    @GetMapping("/admin-dashboard")
    public ModelAndView adminDashboard(ModelAndView modelAndView, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user-number") == null) {
            modelAndView.setViewName("User/Admin/dist/index");
        }
        modelAndView.setViewName("Admin");
        modelAndView.addObject("product", new ProductDTO());
        return modelAndView;
    }

    @PostMapping("/admin-signup")
    public ModelAndView adminSignUp(@ModelAttribute AdminDTO adminDTO, HttpServletRequest httpServletRequest, ModelAndView model) {
        boolean admin = adminService.findAdmin(adminDTO);
        if (admin != false) {
            HttpSession session = httpServletRequest.getSession();
//            model.addObject("admin", new AdminDTO());
            model.setViewName("redirect:admin-dashboard");
        } else {
            model.setViewName("redirect:error");
        }
        return model;
    }

    @PostMapping("/add_products")
    public String productForm(@ModelAttribute ProductDTO product, MultipartFile images) {
        boolean checked = productService.saveProduct(product,images);
        if (checked) {
            return "redirect:/admin-dashboard";
        } else {
            return "404";
        }

    }
    @PostMapping("/delete_products")
    public String deleteProduct(){
        return null;
    }
}
