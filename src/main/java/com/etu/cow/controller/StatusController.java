package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Status;
import com.etu.cow.repository.CourseRepository;
import com.etu.cow.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses/{courseId}/status")
    public ResponseEntity<List> getStatus(@PathVariable Long courseId) {
        List<Status> statusList = statusRepository.findByCourseId(courseId);
        if (statusList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusList,HttpStatus.OK);
    }

    @PostMapping("/courses/{courseId}/status")
    public Status addToStatus(@PathVariable Long courseId,
                                            @RequestBody Status status) {
        return courseRepository.findById(courseId).map(course -> {
            status.setCourse(course);
            return statusRepository.save(status);
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

    }

    @DeleteMapping("/courses/{courseId}/status/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long courseId,
                                          @PathVariable Long id) {
        if(!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id " + courseId);
        }
        return statusRepository.findById(id)
                .map(status -> {
                    statusRepository.delete(status);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Status not found with id " + id));
    }

}
