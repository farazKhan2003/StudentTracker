package com.farazspringlearn.studenttracker.controller;

import com.farazspringlearn.studenttracker.model.Student;
import com.farazspringlearn.studenttracker.repository.StudentRepository;
import com.farazspringlearn.studenttracker.service.StudentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Student Controller which holds all operations for anything relating to the student data.
 */

@RequestMapping("/student")
@RestController
@CrossOrigin()
public class StudentController {
  /**
   * StudentService class injected into the controller class.
   */
  @Autowired
  private StudentService studentService;

  /**
   * StudentRepository class injected into the controller class.
   */
  @Autowired
  private StudentRepository studentRepository;

  /**
   * Parses student information and adds it to the database.
   *
   * @param student student name and email parsed from front end
   * @return String confirmation that student is added
   */

  @PostMapping("/add")
    public String add(@RequestBody Student student) {
    studentService.saveStudent(student);
    return "New student added.";
  }

  /**
   * Gets all student information stored in the database.
   *
   * @return list of all student data.
   */
  @GetMapping("/getall")
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  /**
   * Deletes the student from the database with the matching parsed ID.
   *
   * @param id the ID of the student that should be deleted.
   *
   * @return HTTP status of whether the student was found in the database or not.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable int id) {
    try {
      studentService.deleteStudent(id);
      return ResponseEntity.ok("Student successfully deleted");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not Found");
    }
  }

  /**
     * Alters student name and/or email address from the matching parsed ID.
     *
     * @param id the ID of the student whose details are being altered.
     * @param studentData The id, name and email of the student.
     *
     * @return HTTP status of whether ID was found or not.
   */
  @PutMapping("/alter/{id}")
    public ResponseEntity<Student> alterStudent(@PathVariable int id,
                                                @RequestBody Student studentData) {
    //Temporary student which holds the information held in the Database.
    Optional<Student> optionalStudent = studentRepository.findById(id);
    if (!optionalStudent.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    //update student data with the altered data.
    Student student = optionalStudent.get();
    student.setName(studentData.getName());
    student.setEmail(studentData.getEmail());

    //push that altered data to the database
    studentRepository.save(student);
    return ResponseEntity.ok(student);
  }
}
