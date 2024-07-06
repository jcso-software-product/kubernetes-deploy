package com.jcso.springboot.mvsc.course.models.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estudiantes_cursos")

@Getter
@Setter
public class StudentsCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", unique = true)
    private Long studentId;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof StudentsCourses)){
            return false;
        }

        StudentsCourses o = (StudentsCourses) obj;
        return this.studentId != null
                && this.studentId.equals(o.studentId);
    }

}
