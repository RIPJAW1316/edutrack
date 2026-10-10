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

import com.anurag.cs.dto.DepartmentDTO;
import com.anurag.cs.dto.DepartmentResponseDTO;
import com.anurag.cs.entity.Department;
import com.anurag.cs.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

      @Autowired
      private DepartmentService service;

      @GetMapping
      public List<DepartmentResponseDTO> getAll() {
            return service.getAll();
      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getById(@PathVariable Long id) {
            Department dept = service.getById(id);
            if (dept == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(dept);
      }

      @PostMapping
      public Department createDept(@Valid @RequestBody DepartmentDTO dto) {
            return service.createDept(dto);
      }

      @PutMapping("/{id}")
      public ResponseEntity<?> updateDept(@PathVariable Long id, @Valid @RequestBody DepartmentDTO dto) {
            Department updated = service.updateDept(id, dto);
            if (updated == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteDept(@PathVariable Long id) {
            String result = service.deleteDept(id);
            return ResponseEntity.ok(result);
      }
}