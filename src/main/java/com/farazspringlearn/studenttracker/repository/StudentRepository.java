package com.farazspringlearn.studenttracker.repository;

import com.farazspringlearn.studenttracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for spring to interact with the MySQL database student table.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
