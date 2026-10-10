package com.anurag.cs.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String name;

      private String code;

      private String description;

      @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
      @JsonIgnoreProperties("department")
      private List<Student> students = new ArrayList<>();

      public Department() {
      }

      public Department(String name, String code, String description) {
            this.name = name;
            this.code = code;
            this.description = description;
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

      public List<Student> getStudents() {
            return students;
      }

      public void setStudents(List<Student> students) {
            this.students = students;
      }

      public int getStudentCount() {
            return students != null ? students.size() : 0;
       }
}