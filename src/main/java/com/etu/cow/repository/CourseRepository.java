package com.etu.cow.repository;

import com.etu.cow.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseCode(String cid);
}
