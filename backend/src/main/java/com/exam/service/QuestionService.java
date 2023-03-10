package com.exam.service;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

import java.util.List;
public interface QuestionService{
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public List<Question> getAllQuestions();
    public Question getQuestionById(int questionId);
    public List<Question> getQuestionsOfQuiz(Quiz quiz);
    public void deleteQuestionByQuestionId(int questionId);
}