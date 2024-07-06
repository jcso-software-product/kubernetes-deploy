package com.jcso.springboot.mvsc.course.models.entity;


import com.jcso.springboot.mvsc.course.models.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Table(name = "cursos")
@Entity

@Getter
@Setter
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<StudentsCourses> studentsCourses = new ArrayList<>();

    @Transient
    private List<Student> students = new ArrayList<>();

    public void addStudentCourse(StudentsCourses studentsCourses){
        this.studentsCourses.add(studentsCourses);
    }

    public void removeStudentCourse(StudentsCourses studentsCourses){
        this.studentsCourses.remove(studentsCourses);
    }


}
