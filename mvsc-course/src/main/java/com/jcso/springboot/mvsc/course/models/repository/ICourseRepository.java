package com.jcso.springboot.mvsc.course.models.repository;

import com.jcso.springboot.mvsc.course.models.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query("delete from StudentsCourses sc where sc.studentId=?1")
    void deleteStudentCourseById(Long id);
}
