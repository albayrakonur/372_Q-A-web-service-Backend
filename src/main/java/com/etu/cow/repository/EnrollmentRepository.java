package com.etu.cow.repository;

import com.etu.cow.model.Enrollment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findByCourseCode(String courseCode);
    List<Enrollment> findAll();
}
