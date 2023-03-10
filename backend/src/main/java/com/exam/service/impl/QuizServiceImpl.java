package com.exam.service.impl;

import com.exam.model.exam.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    @Override
    public Quiz updateQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }
    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }
    @Override
    public Quiz getQuizById(int quizId){
    return quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException());
    }
    @Override
    public void deleteQuizById(int quizId){
        /*Quiz deleteQuizByQuizId=quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException());
        quizRepository.delete(deleteQuizByQuizId);*/
        quizRepository.deleteById(quizId);
    }
}