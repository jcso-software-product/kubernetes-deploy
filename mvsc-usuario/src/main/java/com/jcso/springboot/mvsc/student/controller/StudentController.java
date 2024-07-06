package com.jcso.springboot.mvsc.student.controller;


import com.jcso.springboot.mvsc.student.models.entity.Student;
import com.jcso.springboot.mvsc.student.service.IStudentService;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity findAllStudent(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity findStudentById(@PathVariable Long id){
        log.info("findStudentById");
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PostMapping
    public ResponseEntity saveStudent(@Valid @RequestBody Student student, BindingResult result){
        if(result.hasErrors()){
            return studentService.validResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.saveStudent(student));
    }

    @PutMapping
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, BindingResult result){
        if(result.hasErrors()){
            return studentService.validResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.updateStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-course")
    public ResponseEntity getStudentsByCourses(@RequestParam List<Long> ids){
        return ResponseEntity.ok(studentService.findAllStudentsByIds(ids));
    }

}
