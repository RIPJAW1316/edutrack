package com.anurag.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anurag.cs.dto.LoginDTO;
import com.anurag.cs.dto.LoginResponseDTO;
import com.anurag.cs.dto.RegisterDTO;
import com.anurag.cs.dto.StudentResponseDTO;
import com.anurag.cs.entity.Department;
import com.anurag.cs.entity.Student;
import com.anurag.cs.enums.Role;
import com.anurag.cs.repository.DepartmentRepository;
import com.anurag.cs.repository.StudentRepository;

@Service
public class StudentService {

      @Autowired
      private StudentRepository repo;

      @Autowired
      private DepartmentRepository departmentRepo;

      @Autowired
      private JwtService jwtService;

      @Autowired
      private PasswordEncoder passwordEncoder;

      public List<StudentResponseDTO> getAllStudents() {
            return repo.findAll().stream()
                        .map(StudentResponseDTO::new)
                        .collect(Collectors.toList());
      }

      public List<StudentResponseDTO> getStudentsByDepartment(Long departmentId) {
            return repo.findByDepartmentId(departmentId).stream()
                        .map(StudentResponseDTO::new)
                        .collect(Collectors.toList());
      }

      public StudentResponseDTO createStudent(RegisterDTO dto) {
            Student student = new Student();
            student.setName(dto.getName());
            if (dto.getRole() != null && !dto.getRole().isEmpty()) {
                  try {
                        student.setRole(Role.valueOf(dto.getRole().toUpperCase()));
                  } catch (IllegalArgumentException e) {
                        student.setRole(Role.STUDENT);
                  }
            } else {
                  student.setRole(Role.STUDENT);
            }
            student.setEmail(dto.getEmail());
            student.setPassword(passwordEncoder.encode(dto.getPassword()));

            if (dto.getDepartmentId() != null) {
                  Department dept = departmentRepo.findById(dto.getDepartmentId()).orElse(null);
                  student.setDepartment(dept);
            }

            Student saved = repo.save(student);
            return new StudentResponseDTO(saved);
      }

      public StudentResponseDTO getStudentById(Long id) {
            Student student = repo.findById(id).orElse(null);
            return student != null ? new StudentResponseDTO(student) : null;
      }

      public StudentResponseDTO updateStudent(Long id, RegisterDTO dto) {
            Student existingStudent = repo.findById(id).orElse(null);
            if (existingStudent != null) {
                  if (dto.getName() != null && !dto.getName().isEmpty()) {
                        existingStudent.setName(dto.getName());
                  }
                  if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
                        existingStudent.setEmail(dto.getEmail());
                  }
                  if (dto.getRole() != null && !dto.getRole().isEmpty()) {
                        try {
                              existingStudent.setRole(Role.valueOf(dto.getRole().toUpperCase()));
                        } catch (IllegalArgumentException ignored) {
                        }
                  }
                  if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                        existingStudent.setPassword(passwordEncoder.encode(dto.getPassword()));
                  }
                  if (dto.getDepartmentId() != null) {
                        Department dept = departmentRepo.findById(dto.getDepartmentId()).orElse(null);
                        existingStudent.setDepartment(dept);
                  }
                  Student saved = repo.save(existingStudent);
                  return new StudentResponseDTO(saved);
            }
            return null;
      }

      public String deleteStudent(Long id) {
            if (repo.existsById(id)) {
                  repo.deleteById(id);
                  return "Student deleted successfully";
            }
            return "Student not found";
      }

      public ResponseEntity<?> login(LoginDTO dto) {
            Student existingStu = repo.findByEmail(dto.getEmail());
            if (existingStu == null) {
                  return ResponseEntity
                              .status(HttpStatus.UNAUTHORIZED)
                              .body("Email is Wrong");
            }
            boolean passwordMatch = passwordEncoder.matches(dto.getPassword(), existingStu.getPassword());
            if (!passwordMatch) {
                  return ResponseEntity
                              .status(HttpStatus.UNAUTHORIZED)
                              .body("Password is Wrong");
            }
            String token = jwtService.generateToken(dto.getEmail());
            String roleStr = existingStu.getRole() != null ? existingStu.getRole().name() : Role.STUDENT.name();
            LoginResponseDTO response = new LoginResponseDTO(token, existingStu.getId(), existingStu.getEmail(), roleStr);
            return ResponseEntity.ok(response);
      }
}