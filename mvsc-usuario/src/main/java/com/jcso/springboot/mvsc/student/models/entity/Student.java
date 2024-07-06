package com.jcso.springboot.mvsc.student.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "students")

@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotEmpty
    private String name;

    @Column(name = "correo", unique = true)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "clave")
    @NotEmpty
    private String password;

}
