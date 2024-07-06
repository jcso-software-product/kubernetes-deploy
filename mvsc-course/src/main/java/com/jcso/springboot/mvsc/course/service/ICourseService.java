package com.jcso.springboot.mvsc.course.service;

import com.jcso.springboot.mvsc.course.models.Student;
import com.jcso.springboot.mvsc.course.models.entity.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<Course> findAllCourses();
    Course findCourseById(Long id);
    Course findCourseByStudentId(Long id);
    Course saveCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long id);
    ResponseEntity validResult(BindingResult result);
    void deleteStudentCourseById(Long id);


    Student addStudent(Student student, Long courseId);
    Student createStudent(Student student, Long courseId);
    Student removeStudent(Student student, Long courseId);

}
