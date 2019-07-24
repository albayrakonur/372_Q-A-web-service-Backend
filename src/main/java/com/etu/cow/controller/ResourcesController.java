package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Resources;
import com.etu.cow.repository.CourseRepository;
import com.etu.cow.repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ResourcesController {

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses/{courseId}/resources")
    public ResponseEntity<List> getResources(@PathVariable Long courseId) {
        List<Resources> resourcesList = resourcesRepository.findByCourseId(courseId);
        if (resourcesList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resourcesList, HttpStatus.OK);
    }

    @PostMapping("/courses/{courseId}/resources")
    public Resources createQuestion(@PathVariable Long courseId,
                                   @Valid @RequestBody Resources resources) {
        return courseRepository.findById(courseId).map(course -> {
            resources.setCourse(course);
            return resourcesRepository.save(resources);
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }

}
