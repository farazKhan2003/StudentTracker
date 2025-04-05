package com.farazspringlearn.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farazspringlearn.studenttracker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
