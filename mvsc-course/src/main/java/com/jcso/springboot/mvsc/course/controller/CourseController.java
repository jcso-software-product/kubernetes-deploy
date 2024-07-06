package com.jcso.springboot.mvsc.course.controller;

import com.jcso.springboot.mvsc.course.models.Student;
import com.jcso.springboot.mvsc.course.models.entity.Course;
import com.jcso.springboot.mvsc.course.service.ICourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity findAllCourses(){
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity findCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.findCourseByStudentId(id));
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody Course course, BindingResult result){
        if(result.hasErrors()){
            return courseService.validResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.saveCourse(course));
    }

    @PutMapping
    public ResponseEntity updateCourse(@Valid @RequestBody Course course, BindingResult result){
        if(result.hasErrors()){
            return courseService.validResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.updateCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/add-student/{courseId}")
    public ResponseEntity addStudent(@RequestBody Student student, @PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.addStudent(student, courseId));
    }

    @PostMapping("/create-student/{courseId}")
    public ResponseEntity createStudent(@RequestBody Student student, @PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createStudent(student, courseId));
    }

    @DeleteMapping("/remove-student/{courseId}")
    public ResponseEntity removeStudent(@RequestBody Student student, @PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.removeStudent(student, courseId));
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity deleteStudentCourseById(@PathVariable Long id){
        courseService.deleteStudentCourseById(id);
        return ResponseEntity.noContent().build();
    }
}
