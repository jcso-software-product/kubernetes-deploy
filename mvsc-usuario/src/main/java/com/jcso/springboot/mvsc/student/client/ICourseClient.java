package com.jcso.springboot.mvsc.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-course", url = "${msvc.course.url}")
public interface ICourseClient {

    @DeleteMapping("/delete-student/{id}")
    void deleteStudentCourseById(@PathVariable Long id);
}
