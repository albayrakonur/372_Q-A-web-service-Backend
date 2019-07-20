package com.etu.cow.controller;

import com.etu.cow.exception.ResourceNotFoundException;
import com.etu.cow.model.Course;
import com.etu.cow.model.Question;
import com.etu.cow.repository.CourseRepository;
import com.etu.cow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses/{courseId}/questions")
    public ResponseEntity<List> getQuestions(@PathVariable Long courseId) {
        List<Question> questionList = questionRepository.findByCourseId(courseId);
        if (questionList == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }


    @PostMapping("/courses/{courseId}/questions")
    public Question createQuestion(@PathVariable Long courseId,
                                   @Valid @RequestBody Question question) {
        return courseRepository.findById(courseId).map(course -> {
            question.setCourse(course);
            System.out.println(question.toString());
            System.out.println(course.toString());
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId,
                                   @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setMessage(questionRequest.getMessage());
                    question.setAuthor(questionRequest.getAuthor());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}