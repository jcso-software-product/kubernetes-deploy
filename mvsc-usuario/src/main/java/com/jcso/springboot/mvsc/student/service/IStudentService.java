package com.jcso.springboot.mvsc.student.service;

import com.jcso.springboot.mvsc.student.models.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IStudentService {

    List<Student> findAllStudents();
    Student findStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
    ResponseEntity validResult(BindingResult result);

    List<Student> findAllStudentsByIds(Iterable<Long> ids);

}
