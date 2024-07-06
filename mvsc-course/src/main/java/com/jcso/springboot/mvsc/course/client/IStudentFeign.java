package com.jcso.springboot.mvsc.course.client;

import com.jcso.springboot.mvsc.course.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mvsc-student", url = "${mvsc.student.url}")
public interface IStudentFeign {

    @GetMapping("/{id}")
    Student getStudent(@PathVariable Long id);

    @PostMapping
    Student createStudent(@RequestBody Student student);

    @GetMapping("/by-course")
    List<Student> getStudentsByCourses(@RequestParam Iterable<Long> ids);
}
