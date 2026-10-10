package com.anurag.cs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.anurag.cs.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
      Student findByEmail(String email);
      List<Student> findByDepartmentId(Long departmentId);
}
