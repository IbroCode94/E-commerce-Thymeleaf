package com.example.week7springmvc.Service.ServiceImpl;

import com.example.week7springmvc.CloudinaryConfig.Cloudinarys;
import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Model.Product;
import com.example.week7springmvc.Model.User;
import com.example.week7springmvc.Repository.ProductRepository;
import com.example.week7springmvc.Repository.UserRepository;
import com.example.week7springmvc.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public boolean saveProduct(ProductDTO productDTO, MultipartFile file) {
        boolean status=  false;
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        Cloudinarys cloudinarys = new Cloudinarys();
        String url = cloudinarys.cloud(productDTO.getProductName(),file);
        product.setImage(url);

        Product savedProduct = productRepository.save(product);
        if(savedProduct!=null)
            status = true;
        else
            status=false;


        return status;
    }





    @Override
    public List<Product> findAlls() {
        List<Product> productList =  productRepository.findAll();
        return productList;
    }
}
