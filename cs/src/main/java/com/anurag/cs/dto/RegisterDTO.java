package com.anurag.cs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {

      @NotBlank(message = "Name is required")
      private String name;

      private String role;

      @NotBlank(message = "Email is required")
      @Email(message = "Invalid email format")
      private String email;

      @NotBlank(message = "Password is required")
      private String password;

      private Long departmentId;

      public RegisterDTO() {
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getRole() {
            return role;
      }

      public void setRole(String role) {
            this.role = role;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public Long getDepartmentId() {
            return departmentId;
      }

      public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
      }
}