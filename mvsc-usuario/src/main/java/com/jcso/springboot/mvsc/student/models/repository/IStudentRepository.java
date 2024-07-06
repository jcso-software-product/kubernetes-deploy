package com.jcso.springboot.mvsc.student.models.repository;

import com.jcso.springboot.mvsc.student.models.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
