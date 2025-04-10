package com.farazspringlearn.studenttracker.controller;


import com.farazspringlearn.studenttracker.model.Admin;
import com.farazspringlearn.studenttracker.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for Admin face, will allow for login with correct admin data.
 */

@RestController
@CrossOrigin
@RequestMapping("/admin")

public class AdminController {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Login method that receives data from front end and verifies it.
   *
   * @param loginData Login details passed from the front end.
   * @return returns a HTTP response whether the login was successful or not.
  */
  @PostMapping("/login")
  //Using post mapping as it hides the credentials in the body rather than the URL like get mapping.
  public ResponseEntity<?> login(@RequestBody Admin loginData) {
    Admin admin = adminRepository.findByUsername(loginData.getUsername());
    if (admin != null && passwordEncoder.matches(loginData.getPassword(), admin.getPassword())) {
      //returns login successful if entered password matches the hashed password
      return ResponseEntity.ok("Login Successful");
    }

    //returns the UNAUTHORIZED HTTP code if login is unsuccessful
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
  }
}


