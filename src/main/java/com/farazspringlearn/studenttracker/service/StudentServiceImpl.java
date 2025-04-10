package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Student;
import com.farazspringlearn.studenttracker.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The implementation of the studentm service interface.
 */

@Service
public class StudentServiceImpl implements StudentService {


  @Autowired
  //injected student repository
  private StudentRepository studentrepository;

  /**
   * A method to save a student to the student table in the database.
   *
   * @param student The student data to be saved to the database.
   *
   * @return the student data that was saved to the database.
   *
   */
  @Override
  public Student saveStudent(Student student) {
    return studentrepository.save(student);
  }
  /**
   * A method to return all students currently stored in the database.
   *
   * @return A list of all the students in the database.
   */

  @Override
  public List<Student> getAllStudents() {
    return studentrepository.findAll();
  }

  /**
   * A method that deletes a student from the database.
   *
   * @param id The ID of the student that is being deleted.
   */

  @Override
  public void deleteStudent(int id) {
    studentrepository.deleteById(id);
  }
}
