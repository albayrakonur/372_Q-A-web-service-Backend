package com.etu.cow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.etu.cow.model.Student;
import com.etu.cow.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentRepository repository;

    @GetMapping("/student/bulkcreate")
    public String bulkcreate(){
        // save a list of Students
        repository.saveAll(Arrays.asList(new Student("151101070","Onur", "Albayrak","onuralbayrak@etu.edu.tr", "albayrak1")
                , new Student("151101046","Enes", "Karanfil","ekaranfil@etu.edu.tr","karanfil1")
                , new Student("151101032","Ayca", "Gurleyik","agurleyik@etu.edu.tr","gurleyik1")));

        return "Students are created";
    }

    @PostMapping("/student/create")
    public String create(@RequestBody Student student){
        // save a single Student
        repository.save(new Student(student.getSchoolID(),student.getFirstName(), student.getLastName(), student.getEmail(),student.getPassword()));

        return "Student is created";
    }

    @GetMapping("/student/findall")
    public List<Student> findAll(){

        List<Student> students = repository.findAll();
        List<Student> student = new ArrayList<>();

        for (Student studentTemp : students) {
            student.add(new Student(studentTemp.getSchoolID(),studentTemp.getFirstName(), studentTemp.getLastName(), studentTemp.getEmail(), studentTemp.getPassword()));
        }

        return student;
    }

    @GetMapping("/student/search/{id}")
    public String search(@PathVariable long id){
        String user = "";
        user = repository.findById(id).toString();
        return user;
    }

    @GetMapping("/student/searchbyfirstname/{firstname}")
    public List<Student> fetchDataByLastName(@PathVariable String firstname){

        List<Student> students = repository.findByFirstName(firstname);
        List<Student> studentUI = new ArrayList<>();

        for (Student student : students) {
            studentUI.add(new Student(student.getSchoolID(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword()));
        }

        return studentUI;
    }
}