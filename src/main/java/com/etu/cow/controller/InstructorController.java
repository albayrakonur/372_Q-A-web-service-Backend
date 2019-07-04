package com.etu.cow.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.etu.cow.model.Instructor;
import com.etu.cow.repository.InstructorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    @Autowired
    InstructorRepository repository;

    @GetMapping("/instructor/bulkcreate")
    public String bulkcreate(){
        // save a list of Instructors
        repository.saveAll(Arrays.asList(new Instructor("Onur", "Albayrak","onuralbayrak@etu.edu.tr", "albayrak1")
                , new Instructor("Enes", "Karanfil","ekaranfil@etu.edu.tr", "karanfil1")
                , new Instructor("Ayca", "Gurleyik","agurleyik@etu.edu.tr", "gurleyik1")));

        return "Instructors are created";
    }

    @PostMapping("/instructor/create")
    public String create(@RequestBody Instructor instructor){
        // save a single Instructors
        repository.save(new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getPassword()));

        return "Instructor is created";
    }

    @GetMapping("/instructor/findall")
    public List<Instructor> findAll(){

        List<Instructor> instructors = repository.findAll();
        List<Instructor> instructor = new ArrayList<>();

        for (Instructor instructorTemp : instructors) {
            instructor.add(new Instructor(instructorTemp.getFirstName(), instructorTemp.getLastName(), instructorTemp.getEmail(), instructorTemp.getPassword()));
        }

        return instructor;
    }

    @GetMapping("/instructor/search/{id}")
    public String search(@PathVariable long id){
        String instructor = "";
        instructor = repository.findById(id).toString();
        return instructor;
    }

    @GetMapping("/instructor/searchbyfirstname/{firstname}")
    public List<Instructor> fetchDataByLastName(@PathVariable String firstname){

        List<Instructor> instructors = repository.findByFirstName(firstname);
        List<Instructor> instructorUI = new ArrayList<>();

        for (Instructor instructor : instructors) {
            instructorUI.add(new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getPassword()));
        }

        return instructorUI;
    }
}
