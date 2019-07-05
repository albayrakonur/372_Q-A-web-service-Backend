package com.etu.cow.repository;

import com.etu.cow.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {
    List<Course> findByCourseCode(String courseCode);
    List<Course> findAll();
}
