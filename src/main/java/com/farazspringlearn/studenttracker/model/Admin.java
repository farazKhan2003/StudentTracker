package com.farazspringlearn.studenttracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Model class for Administrators, holds the ID, username and password.
 *
 */
@Entity
public class Admin {

  @Id
  @GeneratedValue
  //auto-incremented unique ID
  private int id;

  //created permission variable for different access levels in administrators.
  private int permission;
  private String username;
  private String password;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPermission() {
    return permission;
  }

  public void setPermission(int permission) {
    this.permission = permission;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Constructor for an empty administrator that can be injected where needed.
   */
  public Admin() {
  }
}
