package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Course;
import com.etu.cow.model.CourseList;
import com.etu.cow.repository.CourseListRepository;
import com.etu.cow.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseListController {

    @Autowired
    private CourseListRepository courseListRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses/{courseId}/list")
    public ResponseEntity<List> getList(@PathVariable Long courseId) {

        List<CourseList> result = courseListRepository.findByCourseId(courseId);
        if (result == null) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @PostMapping("/courses/{courseId}/list")
    public CourseList addStudent(@PathVariable Long courseId,
                                 @Valid @RequestBody CourseList courseListRequest) {
        return courseRepository.findById(courseId).map(course -> {
            courseListRequest.setCourse(course);
            return courseListRepository.save(courseListRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }
}
