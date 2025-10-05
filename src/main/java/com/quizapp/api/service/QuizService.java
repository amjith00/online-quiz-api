package com.quizapp.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.api.dto.AnswerSubmission;
import com.quizapp.api.dto.AnswerSubmission.Answer;
import com.quizapp.api.dto.QuestionResponse;
import com.quizapp.api.dto.QuizResponse;
import com.quizapp.api.dto.ScoreResponse;
import com.quizapp.api.model.Question;
import com.quizapp.api.model.QuestionType;
import com.quizapp.api.model.Quiz;
import com.quizapp.api.repository.QuestionRepository;
import com.quizapp.api.repository.QuizRepository;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    
    public List<QuizResponse> getAllQuizzesWithoutAnswers() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
            .map(this::mapToQuizResponse)
            .collect(Collectors.toList());
    }
    
    public Question addQuestion(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));
        
        validateQuestion(question);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }
    
    public List<QuestionResponse> getQuestionsWithoutAnswers(Long quizId) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        return questions.stream()
            .map(this::mapToResponseWithoutAnswers)
            .collect(Collectors.toList());
    }
    
    public ScoreResponse calculateScore(Long quizId, AnswerSubmission submission) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        int score = 0;
        int totalAnswered = submission.getAnswers().size(); 
        
        for (Answer answer : submission.getAnswers()) {
            Question question = findQuestionById(questions, answer.getQuestionId());
            if (isAnswerCorrect(question, answer.getSelectedOption())) {
                score++;
            }
        }
        
        return new ScoreResponse(score, totalAnswered); 
    }
    
    private Question findQuestionById(List<Question> questions, Long questionId) {
        return questions.stream()
            .filter(q -> q.getId().equals(questionId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Question not found: " + questionId));
    }
    
    private boolean isAnswerCorrect(Question question, String selectedOption) {
        return question.getCorrectAnswer().equals(selectedOption);
    }
    
    private QuestionResponse mapToResponseWithoutAnswers(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setText(question.getText());
        response.setType(question.getType());
        response.setOptionA(question.getOptionA());
        response.setOptionB(question.getOptionB());
        response.setOptionC(question.getOptionC());
        response.setOptionD(question.getOptionD());
        return response;
    }
    
    private QuizResponse mapToQuizResponse(Quiz quiz) {
        List<QuestionResponse> questionResponses = quiz.getQuestions().stream()
            .map(this::mapToResponseWithoutAnswers)
            .collect(Collectors.toList());
        
        QuizResponse response = new QuizResponse();
        response.setId(quiz.getId());
        response.setTitle(quiz.getTitle());
        response.setQuestions(questionResponses);
        return response;
    }
    
    private void validateQuestion(Question question) {
        if (question.getType() == QuestionType.TEXT) {
            if (question.getText().length() > 300) {
                throw new ValidationException("Text question exceeds 300 characters");
            }
        }
    }
}

class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}