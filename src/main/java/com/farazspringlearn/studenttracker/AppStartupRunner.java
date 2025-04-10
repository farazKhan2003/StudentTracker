package com.farazspringlearn.studenttracker;

import com.farazspringlearn.studenttracker.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public void run(String... args) throws Exception {
        adminService.createStartAdmin();  
    }
}
