package com.jcso.springboot.mvsc.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MvscUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvscUsuarioApplication.class, args);
	}

}
