package com.etu.cow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "courseCode")
    private String courseCode;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "courseRequirements")
    private ArrayList<String> courseRequirements = new ArrayList<>();

    protected Course() {

    }

    public Course(String courseCode, String courseName, ArrayList<String> courseRequirements) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseRequirements = courseRequirements;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<String> getCourseRequirements() {
        return courseRequirements;
    }

    public void setCourseRequirements(ArrayList<String> courseRequirements) {
        this.courseRequirements = courseRequirements;
    }

    @Override
    public String toString() {
        return String.format("Course[id=%d, courseName='%s', courseCode='%s']", id, courseName, courseCode);
    }
}
