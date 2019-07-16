package com.etu.cow.controller;

import com.etu.cow.model.Instructor;
import com.etu.cow.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InstructorController {
    @Autowired
    InstructorRepository instructorRepository;
    @PostMapping("/instructors")
    public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {
        Instructor tempInstructor = new Instructor();
        tempInstructor.setInstructorFirstName(instructor.getInstructorFirstName());
        tempInstructor.setInstructorLastName(instructor.getInstructorLastName());
        tempInstructor.setInstructorMail(instructor.getInstructorMail());
        tempInstructor.setInstructorPassword(instructor.getInstructorPassword());
        return instructorRepository.save(tempInstructor);

    }
    @GetMapping("/instructors")
    public ResponseEntity<List> getInstructors() {
        List<Instructor> instructorList = instructorRepository.findAll();
        if (instructorList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }
    @GetMapping("/instructors/{id}")
    public Optional<Instructor> searchInstructor(@PathVariable (value = "id") Long id) {
        return instructorRepository.findById(id);

    }

}

