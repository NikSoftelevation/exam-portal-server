package com.exam.controller;

import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController{
    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.addQuiz(quiz),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.updateQuiz(quiz),HttpStatus.ACCEPTED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
       return new ResponseEntity<>(quizService.getAllQuiz(),HttpStatus.OK);
    }
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizByQuizId(@PathVariable("quizId") int quizId){
       return new ResponseEntity<>(quizService.getQuizById(quizId),HttpStatus.OK);
    }
    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuizByQuizId(@PathVariable("quizId") int quizId){
        quizService.deleteQuizById(quizId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}