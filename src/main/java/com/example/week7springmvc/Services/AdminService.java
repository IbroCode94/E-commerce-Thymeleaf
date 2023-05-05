package com.example.week7springmvc.Services;

import com.example.week7springmvc.DTO.AdminDTO;
import com.example.week7springmvc.Model.Admin;

public interface AdminService {
     boolean findAdmin(AdminDTO adminDTO);
}
