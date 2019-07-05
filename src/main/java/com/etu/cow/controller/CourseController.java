package com.etu.cow.controller;

import com.etu.cow.model.Course;
import com.etu.cow.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository repository;

    @GetMapping("/course/bulkcreate")
    public String bulkcreate(){
        // save a list of Courses
        repository.saveAll(Arrays.asList(new Course("Bil103","Bilgisayar Muhendisligine Giris", new ArrayList<>(Arrays.asList("-")))
                , new Course("Bil113","Bilgisayar Programalama I", new ArrayList<>(Arrays.asList("-")))
                , new Course("Bil211","Bilgisayar Programalama II", new ArrayList<>(Arrays.asList("Bil113"))) )
        );
        return "Courses are created";
    }

    @PostMapping("/course/create")
    public String create(@RequestBody Course course) {
        //save a single course
        repository.save(new Course(course.getCourseCode(),course.getCourseName(),course.getCourseRequirements()));
        return "Course is created";
    }

    @GetMapping("/course/findall")
    public List<Course> findAll() {

        List<Course> courses = repository.findAll();
        List<Course> course = new ArrayList<>();
        for (Course courseTemp: courses) {
            course.add(new Course(courseTemp.getCourseCode(),courseTemp.getCourseName(),courseTemp.getCourseRequirements()));
        }
        return course;
    }

    @GetMapping("/course/search/{id}")
    public String search(@PathVariable long id){
        String course = "";
        course = repository.findById(id).toString();
        return course;
    }

    @GetMapping("/course/searchbyfirstname/{courseCode}")
    public List<Course> fetchDataByLastName(@PathVariable String courseCode){

        List<Course> courses = repository.findByCourseCode(courseCode);
        List<Course> course = new ArrayList<>();
        for (Course courseTemp: courses) {
            course.add(new Course(courseTemp.getCourseCode(),courseTemp.getCourseName(),courseTemp.getCourseRequirements()));
        }

        return course;
    }


}