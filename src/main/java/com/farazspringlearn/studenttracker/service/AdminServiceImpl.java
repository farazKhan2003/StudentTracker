package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Admin;
import com.farazspringlearn.studenttracker.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The implementation of the admin service interface.
 */
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  /**
   * Creates the base admin login.
   */
  public void createStartAdmin() {
    String rawPass = "adminpass";
    String hashedPass = passwordEncoder.encode(rawPass);
    Admin admin = new Admin();
    admin.setUsername("admin");
    admin.setPassword(hashedPass);
    adminRepository.save(admin);
  }

  /**
   * Save a new Admin.
   *
   * @param admin the admin data wish to be saved to the database.
   *
   * @return the Admin saved to the database.
   */
  @Override
  public Admin saveNewAdmin(Admin admin) {
    admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    return adminRepository.save(admin);
  }

  /**
   * A method to return all admins currently stored in the database.
   *
   * @return a list of all Admins in the database.
   */
  @Override
  public List<Admin> getAllAdmins() {
    return adminRepository.findAll();
  }

}
