package com.anurag.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.cs.dto.LoginDTO;
import com.anurag.cs.dto.RegisterDTO;
import com.anurag.cs.dto.StudentResponseDTO;
import com.anurag.cs.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

      @Autowired
      private StudentService service;

      @GetMapping
      public List<StudentResponseDTO> getAllStudents() {
            return service.getAllStudents();
      }

      @GetMapping("/department/{departmentId}")
      public List<StudentResponseDTO> getByDepartment(@PathVariable Long departmentId) {
            return service.getStudentsByDepartment(departmentId);
      }

      @PostMapping("/login")
      public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
            return service.login(dto);
      }

      @PostMapping("/register")
      public StudentResponseDTO createStudent(@Valid @RequestBody RegisterDTO dto) {
            return service.createStudent(dto);
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getStudentById(@PathVariable Long id) {
            StudentResponseDTO student = service.getStudentById(id);
            if (student == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody RegisterDTO dto) {
            StudentResponseDTO updated = service.updateStudent(id, dto);
            if (updated == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
            String result = service.deleteStudent(id);
            return ResponseEntity.ok(result);
      }
}