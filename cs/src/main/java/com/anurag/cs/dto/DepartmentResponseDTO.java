package com.anurag.cs.dto;

import com.anurag.cs.entity.Department;

public class DepartmentResponseDTO {

      private Long id;
      private String name;
      private String code;
      private String description;
      private int studentCount;

      public DepartmentResponseDTO() {
      }

      public DepartmentResponseDTO(Department dept) {
            if (dept != null) {
                  this.id = dept.getId();
                  this.name = dept.getName();
                  this.code = dept.getCode();
                  this.description = dept.getDescription();
                  this.studentCount = dept.getStudents() != null ? dept.getStudents().size() : 0;
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

      public String getCode() {
            return code;
      }

      public void setCode(String code) {
            this.code = code;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public int getStudentCount() {
            return studentCount;
      }

      public void setStudentCount(int studentCount) {
            this.studentCount = studentCount;
      }
}
