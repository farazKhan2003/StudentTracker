package com.farazspringlearn.studenttracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Student model class which holds the students name and email.
 */
@Entity
public class Student {
  @Id
  @GeneratedValue
  //auto-generated, unique ID
  private int id;
  private String name;
  private String email;

  /**
   * Constructor class for an empty student.
   */
  public Student() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
