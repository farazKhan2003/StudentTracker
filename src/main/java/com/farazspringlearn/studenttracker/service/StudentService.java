package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    Student saveStudent(Student student);
}
