package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Student;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service interface for springboot, holds all the student functions.
 */
@Service
public interface StudentService {

  /**
   * A method to save a student to the student table in the database.
   *
   * @param student The student data to be saved to the database.
   *
   * @return the student data that was saved to the database.
   *
   */

  Student saveStudent(Student student);

  /**
   * A method to return all students currently stored in the database.
   *
   * @return A list of all the students in the database.
   */
  List<Student> getAllStudents();

  /**
   * A method that deletes a student from the database.
   *
   * @param id The ID of the student that is being deleted.
   */
  void deleteStudent(int id);
}
