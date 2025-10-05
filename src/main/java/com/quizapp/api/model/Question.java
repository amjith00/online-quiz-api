package com.quizapp.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;
    
    @Enumerated(EnumType.STRING)
    private QuestionType type; 
    
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore 
    private Quiz quiz;

    
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    
    @JsonIgnore
    private String correctAnswer;
    
    public Question() {
    }

    public Question(String text, QuestionType type, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.text = text;
        this.type = type;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
    	return id; 
    	}
    public void setId(Long id) {
    	this.id = id; 
    	}
    
    public String getText() { 
    	return text; 
    	}
    public void setText(String text) { 
    	this.text = text; 
    	}
    
    public QuestionType getType() { 
    	return type; 
    	}
    public void setType(QuestionType type) {
    	this.type = type; 
    	}
    
    public Quiz getQuiz() {
    	return quiz; 
    	}
    public void setQuiz(Quiz quiz) {
    	this.quiz = quiz; 
    	}
    

    
    public String getOptionA() { 
    	return optionA;
    	}
    public void setOptionA(String optionA) { 
    	this.optionA = optionA;
    }
    
    public String getOptionB() {
    	return optionB;
    	}
    public void setOptionB(String optionB) { 
    	this.optionB = optionB;
    	}
    
    public String getOptionC() {
    	return optionC; 
    	}
    public void setOptionC(String optionC) {
    	this.optionC = optionC; 
    	}
    
    public String getOptionD() {
    	return optionD; 
    	}
    public void setOptionD(String optionD) {
    	this.optionD = optionD; 
    	}
    
    public String getCorrectAnswer() {
    	return correctAnswer; 
    	}
    public void setCorrectAnswer(String correctAnswer) { 
    	this.correctAnswer = correctAnswer;
    	}
}