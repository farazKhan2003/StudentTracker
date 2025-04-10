package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Admin;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service interface for springboot, holds all the admin functions.
 */
@Service
public interface AdminService {

  /**
   * Save a new Admin.
   *
   * @param admin the admin data wish to be saved to the database.
   *
   * @return the Admin saved to the database.
   */
  Admin saveNewAdmin(Admin admin);

  /**
   * A method to return all admins currently stored in the database.
   *
   * @return a list of all Admins in the database.
   */
  List<Admin> getAllAdmins();

  /**
   * Creates the base admin login.
   */
  void createStartAdmin();
}
