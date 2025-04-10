package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Admin;
import com.farazspringlearn.studenttracker.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   /* @Override
    public void createStartAdmin() {
        String rawPass = "adminpass";
        String hashedPass = passwordEncoder.encode(rawPass);
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(hashedPass);

        adminRepository.save(admin);
    }*/
;
    @Override
    public Admin saveNewAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
