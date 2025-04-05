package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Student;
import com.farazspringlearn.studenttracker.repository.StudentRepository;
import com.farazspringlearn.studenttracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentrepository;

    @Override
    public Student saveStudent(Student student) {
        return studentrepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentrepository.findAll();
    }
}
