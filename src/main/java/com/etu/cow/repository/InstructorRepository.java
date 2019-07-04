package com.etu.cow.repository;

import java.util.List;

import com.etu.cow.model.Instructor;

import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long>{
    List<Instructor> findByFirstName(String FirstName);
    List<Instructor> findAll();
}