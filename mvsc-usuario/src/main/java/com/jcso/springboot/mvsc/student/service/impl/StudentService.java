package com.jcso.springboot.mvsc.student.service.impl;

import com.jcso.springboot.mvsc.student.client.ICourseClient;
import com.jcso.springboot.mvsc.student.models.entity.Student;
import com.jcso.springboot.mvsc.student.models.repository.IStudentRepository;
import com.jcso.springboot.mvsc.student.service.IStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StudentService implements IStudentService {


    private final IStudentRepository studentRepository;
    private final ICourseClient courseClient;

    public StudentService(IStudentRepository studentRepository,
                          ICourseClient courseClient) {
        this.studentRepository = studentRepository;
        this.courseClient = courseClient;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Estudiante identificado %id no encontrado", id)));
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        courseClient.deleteStudentCourseById(id);
    }

    @Override
    public ResponseEntity validResult(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors()
                .forEach(err ->
                    errores.put(err.getField(), "El campo: ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()))
                );
        return ResponseEntity.badRequest().body(errores);
    }

    @Override
    public List<Student> findAllStudentsByIds(Iterable<Long> ids) {
        return studentRepository.findAllById(ids);
    }
}
