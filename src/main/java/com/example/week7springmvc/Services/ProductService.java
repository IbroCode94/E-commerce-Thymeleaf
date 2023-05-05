package com.example.week7springmvc.Services;

import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    boolean saveProduct(ProductDTO productDTO, MultipartFile file);

    List<Product> findAlls();
}
