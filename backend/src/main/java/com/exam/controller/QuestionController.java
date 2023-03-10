package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionByQuestionId(@PathVariable("questionId") int questionId) {
        return new ResponseEntity<>(questionService.getQuestionById(questionId), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") int quizId) {
        Quiz quiz = quizService.getQuizById(quizId);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestionByQuestionId(@PathVariable("questionId") int questionId) {
        questionService.deleteQuestionByQuestionId(questionId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
    //evaluate quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        for (Question q : questions) {
            //single question
            Question question = questionService.getQuestionById(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())) {
                //correct
                correctAnswers++;
                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksGot += marksSingle;
            }
            if (q.getGivenAnswer() != null) {
                attempted++;
            }
        }
        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}