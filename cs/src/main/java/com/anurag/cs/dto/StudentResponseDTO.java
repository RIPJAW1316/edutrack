package com.anurag.cs.dto;

import com.anurag.cs.entity.Student;
import com.anurag.cs.enums.Role;

public class StudentResponseDTO {

      private Long id;
      private String name;
      private String email;
      private Role role;
      private Long departmentId;
      private String departmentName;
      private String departmentCode;

      public StudentResponseDTO() {
      }

      public StudentResponseDTO(Student student) {
            if (student != null) {
                  this.id = student.getId();
                  this.name = student.getName();
                  this.email = student.getEmail();
                  this.role = student.getRole();
                  if (student.getDepartment() != null) {
                        this.departmentId = student.getDepartment().getId();
                        this.departmentName = student.getDepartment().getName();
                        this.departmentCode = student.getDepartment().getCode();
                  }
            }
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public Role getRole() {
            return role;
      }

      public void setRole(Role role) {
            this.role = role;
      }

      public Long getDepartmentId() {
            return departmentId;
      }

      public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
      }

      public String getDepartmentName() {
            return departmentName;
      }

      public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
      }

      public String getDepartmentCode() {
            return departmentCode;
      }

      public void setDepartmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
      }
}
