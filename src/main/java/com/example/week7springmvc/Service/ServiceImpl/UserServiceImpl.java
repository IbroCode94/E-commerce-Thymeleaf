package com.example.week7springmvc.Service.ServiceImpl;

import com.example.week7springmvc.DTO.UserDTO;
import com.example.week7springmvc.Model.User;
import com.example.week7springmvc.Repository.UserRepository;
import com.example.week7springmvc.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String save(UserDTO userDTO) {
        String status;
        User user = new User(userDTO.getUsername(),userDTO.getEmail(), userDTO.getPassword());
        if (userDTO.getEmail().isEmpty() || userDTO.getEmail().isBlank()){
            status="Email is empty";

        } else if (userDTO.getPassword().isBlank() || userDTO.getPassword().isEmpty()) {
            status="Password Cannot be empty";
        }else {
             userRepository.save(user);
             status="successful";
        }
        return status;
    }

    @Override
    public User findUser(UserDTO userDTO) {
        User user =  userRepository.findUserByEmail(userDTO.getEmail()).get();
        //TODO:EXPLAIN REASON FOR ERROR WITH HASHING
        boolean match = user.checkpassword(userDTO.getPassword(), user.getPassword());
        if (match){
            return user;
        }
        return null;
    }
}
