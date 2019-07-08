package com.etu.cow.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "courseCode")
    private String courseCode;

    @Column(name = "studentList")
    private String studentList;

    protected Enrollment() {}

    public Enrollment(String courseCode, String studentList) {
        this.courseCode = courseCode;
        this.studentList = studentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStudentList() {
        return studentList;
    }

    public void setStudentList(String studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return String.format("Enrolment[id=%d, courseCode='%s', studentList='%s']", id, courseCode, studentList);
    }

}
