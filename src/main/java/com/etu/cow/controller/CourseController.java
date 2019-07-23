package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Course;
import com.etu.cow.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public ResponseEntity<List> getCourses() {
        List<Course> courseList = courseRepository.findAll();
        if (courseList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Long courseId,
                                   @Valid @RequestBody Course courseRequest) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.setCourseCode(courseRequest.getCourseCode());
                    course.setCourseName(courseRequest.getCourseName());
                    course.setCourseSemester(courseRequest.getCourseSemester());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    courseRepository.delete(course);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + courseId));
    }

}
