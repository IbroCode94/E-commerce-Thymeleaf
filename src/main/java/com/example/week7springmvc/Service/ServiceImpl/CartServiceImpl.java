package com.example.week7springmvc.Service.ServiceImpl;

import com.example.week7springmvc.DTO.ProductDTO;
import com.example.week7springmvc.Model.Cart;
import com.example.week7springmvc.Model.Product;
import com.example.week7springmvc.Model.User;

import com.example.week7springmvc.Repository.CartRepository;
import com.example.week7springmvc.Repository.ProductRepository;
import com.example.week7springmvc.Repository.UserRepository;
import com.example.week7springmvc.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
//    private final ModelMapper modelMapper;
    @Override
    public Cart addCart(Long userid,Long productId) {
        Optional<User> find = userRepository.findById(userid);
        Optional<Product> product = productRepository.findById(productId);

        if(find.isPresent() && product.isPresent()){
           Optional<Cart> cartAdd = cartRepository.findByUserIdAndProductId(userid,productId);
           if(cartAdd.isPresent() ){
               Cart cart = cartAdd.get();
               cart.setQuantity(cartAdd.get().getQuantity()+1);
               return cartRepository.save(cart);
           }else{
               Cart cart = new Cart();
               cart.setQuantity(1);
               cart.setUser(find.get());
               cart.setProduct(product.get());
               return cartRepository.save(cart);
           }
        }else
            return null;
    }

    @Override
    public List<ProductDTO> getProductById(Long id) {
        List<Cart> cart = cartRepository.findCartsy(id);
        List<ProductDTO> list = new ArrayList<>();
        for (int i = 0; i < cart.size(); i++) {
            Product productList = productRepository.findProductById(cart.get(i).getProduct().getId());
            ProductDTO product = new ProductDTO();
            product.setProductName(productList.getProductName());
            product.setQuantity(cart.get(i).getQuantity());
            product.setCategory(productList.getCategory());
            product.setImage(productList.getImage());
            product.setPrice(productList.getPrice());

            list.add(product);
        }
        return list;
    }

}
