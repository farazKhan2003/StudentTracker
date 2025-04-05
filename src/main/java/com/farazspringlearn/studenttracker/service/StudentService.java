package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
}
