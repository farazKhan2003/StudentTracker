package com.farazspringlearn.studenttracker.repository;

import com.farazspringlearn.studenttracker.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
