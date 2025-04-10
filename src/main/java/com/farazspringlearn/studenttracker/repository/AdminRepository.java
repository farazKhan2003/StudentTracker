package com.farazspringlearn.studenttracker.repository;

import com.farazspringlearn.studenttracker.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for spring to interact with the MySQL database admin table.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

  /**
   * Method to look up an admins data by their username.
   *
   * @param username the username of the admin account.
   *
   * @return the Admin object data of the username given.
   */
  Admin findByUsername(String username);
}


