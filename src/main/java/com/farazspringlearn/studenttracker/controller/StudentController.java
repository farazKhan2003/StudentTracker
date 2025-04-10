package com.farazspringlearn.studenttracker.controller;


import com.farazspringlearn.studenttracker.repository.StudentRepository;
import com.farazspringlearn.studenttracker.service.StudentService;
import com.farazspringlearn.studenttracker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
@RestController
@CrossOrigin()
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "New student added.";
    }

    @GetMapping("/getall")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not Found");
        }
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<Student> alterStudent(@PathVariable int id, @RequestBody Student studentData) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Student student = optionalStudent.get();
        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());

        studentRepository.save(student);
        System.out.println("Updating student with id: " + id);
        return ResponseEntity.ok(student);
    }
 }
