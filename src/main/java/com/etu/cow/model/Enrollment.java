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

    @Column(name = "courseID")
    private String courseID;

    @Column(name = "studentList")
    private ArrayList<String> studentList;

    protected Enrollment() {}

    public Enrollment(String courseID, ArrayList<String> studentList) {
        this.courseID = courseID;
        this.studentList = studentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return String.format("Enrolment[id=%d, courseID='%s', studentList='%s']", id, courseID, studentList);
    }

}
