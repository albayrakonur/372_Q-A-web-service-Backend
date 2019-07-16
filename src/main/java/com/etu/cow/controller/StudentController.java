package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Student;
import com.etu.cow.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        Student tempStudent = new Student();
        tempStudent.setSchoolId(student.getSchoolId());
        tempStudent.setStudentFirstName(student.getStudentFirstName());
        tempStudent.setStudentLastName(student.getStudentLastName());
        tempStudent.setStudentMail(student.getStudentMail());
        tempStudent.setStudentPassword(student.getStudentPassword());
        return studentRepository.save(tempStudent);


    }

    @GetMapping("/students")
    public ResponseEntity<List> getStudents(){
        List<Student> studentList = studentRepository.findAll();
        if (studentList == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/students/{schoolId}")
    public ResponseEntity<Student> getStudent(@PathVariable (value = "schoolId") String schoolId) {
        Student result = studentRepository.findBySchoolId(schoolId);
        if(result == null) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@Valid @PathVariable Long id) {
        return studentRepository.findById(id).map(post -> {
            studentRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Student id " + id + " not found"));
    }


}