package com.jcso.springboot.mvsc.course.service.impl;

import com.jcso.springboot.mvsc.course.client.IStudentFeign;
import com.jcso.springboot.mvsc.course.models.Student;
import com.jcso.springboot.mvsc.course.models.entity.Course;
import com.jcso.springboot.mvsc.course.models.entity.StudentsCourses;
import com.jcso.springboot.mvsc.course.models.repository.ICourseRepository;
import com.jcso.springboot.mvsc.course.service.ICourseService;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final IStudentFeign studentClient;

    public CourseService(ICourseRepository courseRepository,
                         IStudentFeign studentClient) {
        this.courseRepository = courseRepository;
        this.studentClient = studentClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe registrado el curso " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Course findCourseByStudentId(Long id) {
        Course course = findCourseById(id);
        if(!course.getStudentsCourses().isEmpty()){
            List<Long> ids = course.getStudentsCourses()
                    .stream()
                    .map(StudentsCourses::getStudentId)
                    .collect(Collectors.toList());

            List<Student> students = studentClient.getStudentsByCourses(ids);
            course.setStudents(students);
        }
        return course;
    }

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        Course update = findCourseById(course.getId());
        update.setName(course.getName());
        return courseRepository.save(update);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
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
    @Transactional
    public void deleteStudentCourseById(Long id) {
        courseRepository.deleteStudentCourseById(id);
    }

    @Override
    @Transactional
    public Student addStudent(Student student, Long courseId) {

        Course course = findCourseById(courseId);
        Student studentFeign;
        try {
            studentFeign = studentClient.getStudent(student.getId());
        }catch (FeignException fe){
            throw new IllegalArgumentException("Error en la comunicación con el cliente: " + fe.getLocalizedMessage());
        }

        StudentsCourses studentsCourses = new StudentsCourses();
        studentsCourses.setStudentId(studentFeign.getId());

        course.addStudentCourse(studentsCourses);

        courseRepository.save(course);

        return studentFeign;
    }

    @Override
    @Transactional
    public Student createStudent(Student student, Long courseId) {
        Course course = findCourseById(courseId);
        Student studentFeign;
        try {
            studentFeign = studentClient.createStudent(student);
        }catch (FeignException fe){
            throw new IllegalArgumentException("Error en la comunicación con el cliente: " + fe.getLocalizedMessage());
        }

        StudentsCourses studentsCourses = new StudentsCourses();
        studentsCourses.setStudentId(studentFeign.getId());

        course.addStudentCourse(studentsCourses);

        courseRepository.save(course);

        return studentFeign;
    }

    @Override
    @Transactional
    public Student removeStudent(Student student, Long courseId) {
        Course course = findCourseById(courseId);
        Student studentFeign;
        try {
             studentFeign = studentClient.getStudent(student.getId());
        }catch (FeignException fe){
            throw new IllegalArgumentException("Error en la comunicación con el cliente: " + fe.getLocalizedMessage());
        }

        StudentsCourses studentsCourses = new StudentsCourses();
        studentsCourses.setStudentId(studentFeign.getId());

        course.removeStudentCourse(studentsCourses);

        courseRepository.save(course);

        return studentFeign;
    }
}
