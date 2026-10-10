package com.anurag.cs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anurag.cs.entity.Department;
import com.anurag.cs.entity.Student;
import com.anurag.cs.enums.Role;
import com.anurag.cs.repository.DepartmentRepository;
import com.anurag.cs.repository.StudentRepository;

@Configuration
public class DataInitializer implements CommandLineRunner {

      @Autowired
      private DepartmentRepository departmentRepo;

      @Autowired
      private StudentRepository studentRepo;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Override
      public void run(String... args) {
            // Seed Departments if empty
            if (departmentRepo.count() == 0) {
                  Department cse = departmentRepo.save(new Department(
                              "Computer Science & Engineering",
                              "CSE",
                              "Core computing systems, algorithms, software engineering, and AI."
                  ));
                  Department it = departmentRepo.save(new Department(
                              "Information Technology",
                              "IT",
                              "Information architectures, cloud systems, and data analytics."
                  ));
                  departmentRepo.save(new Department(
                              "Electronics & Communication",
                              "ECE",
                              "VLSI, embedded systems, signal processing, and IoT."
                  ));
                  departmentRepo.save(new Department(
                              "Mechanical Engineering",
                              "MECH",
                              "Robotics, thermal engineering, CAD/CAM, and mechanics."
                  ));

                  // Seed Demo Admin Account
                  if (studentRepo.findByEmail("admin@edutrack.com") == null) {
                        Student admin = new Student();
                        admin.setName("System Administrator");
                        admin.setEmail("admin@edutrack.com");
                        admin.setPassword(passwordEncoder.encode("admin123"));
                        admin.setRole(Role.ADMIN);
                        admin.setDepartment(it);
                        studentRepo.save(admin);
                  }

                  // Seed Demo Student Account
                  if (studentRepo.findByEmail("student@edutrack.com") == null) {
                        Student student = new Student();
                        student.setName("Aryan Reddy");
                        student.setEmail("student@edutrack.com");
                        student.setPassword(passwordEncoder.encode("student123"));
                        student.setRole(Role.STUDENT);
                        student.setDepartment(cse);
                        studentRepo.save(student);
                  }
            }
      }
}
