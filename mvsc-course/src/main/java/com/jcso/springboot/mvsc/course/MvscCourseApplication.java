package com.jcso.springboot.mvsc.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MvscCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvscCourseApplication.class, args);
	}

}
