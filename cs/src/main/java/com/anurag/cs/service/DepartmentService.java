package com.anurag.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anurag.cs.dto.DepartmentDTO;
import com.anurag.cs.dto.DepartmentResponseDTO;
import com.anurag.cs.entity.Department;
import com.anurag.cs.entity.Student;
import com.anurag.cs.repository.DepartmentRepository;
import com.anurag.cs.repository.StudentRepository;

@Service
public class DepartmentService {

      @Autowired
      private DepartmentRepository repo;

      @Autowired
      private StudentRepository studentRepo;

      public Department createDept(DepartmentDTO dto) {
            Department dept = new Department();
            dept.setName(dto.getName());
            dept.setCode(dto.getCode());
            dept.setDescription(dto.getDescription());
            return repo.save(dept);
      }

      public List<DepartmentResponseDTO> getAll() {
            return repo.findAll().stream()
                        .map(DepartmentResponseDTO::new)
                        .collect(Collectors.toList());
      }

      public Department getById(Long id) {
            return repo.findById(id).orElse(null);
      }

      public Department updateDept(Long id, DepartmentDTO dto) {
            Department existing = repo.findById(id).orElse(null);
            if (existing != null) {
                  existing.setName(dto.getName());
                  existing.setCode(dto.getCode());
                  existing.setDescription(dto.getDescription());
                  return repo.save(existing);
            }
            return null;
      }

      public String deleteDept(Long id) {
            Department dept = repo.findById(id).orElse(null);
            if (dept != null) {
                  // Unlink students in this department before deleting
                  List<Student> students = studentRepo.findByDepartmentId(id);
                  for (Student s : students) {
                        s.setDepartment(null);
                        studentRepo.save(s);
                  }
                  repo.delete(dept);
                  return "Department deleted successfully";
            }
            return "Department not found";
      }
}