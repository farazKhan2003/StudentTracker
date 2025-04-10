package com.farazspringlearn.studenttracker.controller;


import com.farazspringlearn.studenttracker.model.Admin;
import com.farazspringlearn.studenttracker.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Admin loginData) {
            Admin admin = adminRepository.findByUsername(loginData.getUsername());
            if (admin != null && passwordEncoder.matches(loginData.getPassword(), admin.getPassword())) {
                return ResponseEntity.ok("Login Successful");
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }


