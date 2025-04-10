package com.farazspringlearn.studenttracker.service;

import com.farazspringlearn.studenttracker.model.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {


    Admin saveNewAdmin(Admin admin);
    List<Admin> getAllAdmins();
    void createStartAdmin();
}
