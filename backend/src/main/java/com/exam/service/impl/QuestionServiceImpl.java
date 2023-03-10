package com.exam.service.impl;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.repository.QuestionRepository;
import com.exam.repository.QuizRepository;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public Question updateQuestion(Question question){
        return questionRepository.save(question);
    }
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @Override
    public Question getQuestionById(int questionId) {
        return questionRepository.getOne(questionId);
    }
    @Override
    public List<Question> getQuestionsOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }
    @Override
    public void deleteQuestionByQuestionId(int questionId){
      Question deleteQuestionByQuestionId = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException());
       questionRepository.delete(deleteQuestionByQuestionId);
    }
}