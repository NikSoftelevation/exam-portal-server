package com.exam.service;

import com.exam.model.exam.Quiz;

import java.util.List;
public interface QuizService{
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public List<Quiz> getAllQuiz();
    public Quiz getQuizById(int quizId);
    public void deleteQuizById(int quizId);
}