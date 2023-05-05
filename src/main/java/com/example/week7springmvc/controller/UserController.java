package com.example.week7springmvc.controller;

import com.example.week7springmvc.DTO.AdminDTO;
import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.DTO.UserDTO;
import com.example.week7springmvc.Model.Admin;
import com.example.week7springmvc.Model.Product;
import com.example.week7springmvc.Model.User;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class UserController {


        private UserService userService;
        private AdminService adminService;
        private ProductService productService;

     @Autowired
    public UserController(UserService userService, AdminService adminService, ProductService productService) {
        this.userService = userService;
        this.adminService = adminService;
        this.productService = productService;
    }

//Getting the index page view
        @GetMapping("/")
        public ModelAndView Home(HttpSession session,  ModelAndView modelAndView){
            if(session.getAttribute("user-number")!= null){
                modelAndView.setViewName("redirect:/dashboard");
            }

            modelAndView.setViewName("index");
            modelAndView.addObject("user", new UserDTO());

            return modelAndView;
        }

    @GetMapping("/dashboard")
    public ModelAndView Dashboard(HttpSession session,ModelAndView modelAndView){
        if(session.getAttribute("user-number")== null){
            modelAndView.setViewName("redirect:/");
        }
        modelAndView.setViewName("User/index");
        modelAndView.addObject("list",productService.findAlls());
        modelAndView.addObject("user",new UserDTO());


        return modelAndView;
    }

        @PostMapping("/sign-up")
        public ModelAndView signUp(@ModelAttribute("user") UserDTO userDTO, ModelAndView model){
            String checked = userService.save(userDTO);
            if(checked.equals("successful")) {
                model.setViewName("redirect:/");
            }else{
                model.setViewName("redirect:error");
            }
            return model;
        }



    @GetMapping("/error")
    public ModelAndView error(ModelAndView model){
        model.setViewName("error");
        model.addObject("status", "error_occured");
        return model;
    }





        @PostMapping("/login")
        public ModelAndView loginUser(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest httpServletRequest, ModelAndView model){
            User user = userService.findUser(userDTO);
            if (user!=null){
                HttpSession session = httpServletRequest.getSession();
                Long id = user.getId();

                session.setAttribute("user-number", id);
                System.out.println(session.getAttribute("user-number"));
//            List<Product> productList = productService.findAllByUserId(id);
//            if(productList==null){
//                productList = new ArrayList<>();
//            }
                model.addObject("user", new UserDTO());
                model.setViewName("User/index");
            }




       return model;
        }


        @GetMapping("/logout")
            public String logout(HttpServletRequest httpServletRequest){
            HttpSession session = httpServletRequest.getSession();
            session.removeAttribute("user-number");
            return "redirect:/";
        }
    }


