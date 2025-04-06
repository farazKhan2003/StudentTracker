package com.farazspringlearn.studenttracker.controller;


import com.farazspringlearn.studenttracker.model.Admin;
import com.farazspringlearn.studenttracker.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Admin loginData) {
            Admin admin = adminRepository.findByUsername(loginData.getUsername());

            if (admin != null && admin.getPassword().equals(loginData.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
    }


