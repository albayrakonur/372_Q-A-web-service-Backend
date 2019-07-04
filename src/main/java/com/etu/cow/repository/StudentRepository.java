package com.etu.cow.repository;

import java.util.List;

import com.etu.cow.model.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{
    List<Student> findByFirstName(String FirstName);
    List<Student> findAll();
}