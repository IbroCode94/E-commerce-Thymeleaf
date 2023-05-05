package com.example.week7springmvc.Services;

import com.example.week7springmvc.DTO.UserDTO;
import com.example.week7springmvc.Model.User;

public interface UserService {
    String save(UserDTO userDTO);
    User findUser(UserDTO userDTO);
}
