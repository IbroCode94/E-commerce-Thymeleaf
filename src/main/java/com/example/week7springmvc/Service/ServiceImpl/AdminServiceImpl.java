package com.example.week7springmvc.Service.ServiceImpl;

import com.example.week7springmvc.DTO.AdminDTO;
import com.example.week7springmvc.Model.Admin;
import com.example.week7springmvc.Repository.AdminRepository;
import com.example.week7springmvc.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {

        this.adminRepository = adminRepository;
    }
    @Override
    public boolean findAdmin(AdminDTO adminDTO) {
        boolean status=false;
        Admin admin = adminRepository.findByEmail(adminDTO.getEmail()).get();
        if(admin.getEmail().equals("admin@gmail.com") && admin.getPassword().equals("12345")) {
            status=  true;
        }else {
            status=false;
        }
return  status;
    }
}
