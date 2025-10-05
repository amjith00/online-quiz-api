package com.quizapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quizapp.api.dto.AnswerSubmission;
import com.quizapp.api.dto.QuestionResponse;
import com.quizapp.api.dto.QuizResponse;
import com.quizapp.api.dto.ScoreResponse;
import com.quizapp.api.model.Question;
import com.quizapp.api.model.Quiz;
import com.quizapp.api.service.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    
    @Autowired
    private QuizService quizService;
    
    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        List<QuizResponse> quizzes = quizService.getAllQuizzesWithoutAnswers(); 
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        quizService.createQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully");
    }
    
    @PostMapping("/{quizId}/questions")
    public ResponseEntity<QuestionResponse> addQuestion( 
            @PathVariable Long quizId, 
            @RequestBody Question question) {
        Question savedQuestion = quizService.addQuestion(quizId, question);
        
        QuestionResponse response = new QuestionResponse();
        response.setId(savedQuestion.getId());
        response.setText(savedQuestion.getText());
        response.setType(savedQuestion.getType());
        response.setOptionA(savedQuestion.getOptionA());
        response.setOptionB(savedQuestion.getOptionB());
        response.setOptionC(savedQuestion.getOptionC());
        response.setOptionD(savedQuestion.getOptionD());
        
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(
            @PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuestionsWithoutAnswers(quizId));
    }
    
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<ScoreResponse> submitAnswers(
            @PathVariable Long quizId,
            @RequestBody AnswerSubmission submission) {
        return ResponseEntity.ok(quizService.calculateScore(quizId, submission));
    }
    

}
