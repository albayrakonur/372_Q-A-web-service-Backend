package com.etu.cow.repository;

import com.etu.cow.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    List<Resources> findByCourseId(Long courseId);
}
