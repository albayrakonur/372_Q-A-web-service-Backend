package com.etu.cow.repository;

import com.etu.cow.model.CourseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseListRepository extends JpaRepository<CourseList, Long> {
    List<CourseList> findByCourseId(Long courseId);
}
