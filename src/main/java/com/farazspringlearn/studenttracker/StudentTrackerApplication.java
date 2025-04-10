package com.farazspringlearn.studenttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Driver class for springboot.
 */
@SpringBootApplication
public class StudentTrackerApplication {
  /**
   * Main method that will run the studentTrackerApplication as a Spring Application.
   *
   * @param args Command line arguments passed to the application.
  */
  public static void main(String[] args) {
    SpringApplication.run(StudentTrackerApplication.class, args);
  }

}
