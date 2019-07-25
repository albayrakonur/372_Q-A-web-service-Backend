package com.etu.cow.repository;

import com.etu.cow.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findByCourseId(Long courseId);
    Status findBySchoolId(String sid);
}
