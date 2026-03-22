package com.dphuong.go.gscores.repository;

import com.dphuong.go.gscores.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
