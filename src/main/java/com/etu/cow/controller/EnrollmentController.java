package com.etu.cow.controller;

import com.etu.cow.model.Enrollment;
import com.etu.cow.model.Student;
import com.etu.cow.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class EnrollmentController {
    @Autowired
    EnrollmentRepository repository;

    @GetMapping("/enrollment/bulkcreate")
    public String bulkcreate() {
        //save a list of Enrollments
        repository.saveAll(Arrays.asList(new Enrollment("Bil113","151101070,151101046,151101032")));
        return "Enrollments are created";
    }

    @PostMapping("/enrollment/create")
    public String create(@RequestBody Enrollment enrollment) {
        //save a single Enrollment
        repository.save(new Enrollment(enrollment.getCourseCode(),enrollment.getStudentList()));
        return "Enrollment is created";
    }

    @GetMapping("/enrollment/findall")
    public List<Enrollment> findAll() {
        List<Enrollment> enrollments = repository.findAll();
        List<Enrollment> enrollment = new ArrayList<>();
        for (Enrollment enrollmentTemp : enrollments) {
            enrollment.add(new Enrollment(enrollmentTemp.getCourseCode(),enrollmentTemp.getStudentList()));
        }
        return enrollment;
    }

    @GetMapping("/enrollment/search/{id}")
    public String search(@PathVariable long id) {
        String enrollment = repository.findById(id).toString();
        return enrollment;
    }

}
