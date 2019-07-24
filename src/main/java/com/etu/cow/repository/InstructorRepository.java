package com.etu.cow.repository;

import com.etu.cow.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Instructor findByInstructorMail(String mail);
    Instructor findByInstructorMailAndInstructorPassword(String mail, String psswd);
}
