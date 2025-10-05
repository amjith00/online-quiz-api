package com.quizapp.api.dto;

import java.util.List;

public class QuizResponse {
    private Long id;
    private String title;
    private List<QuestionResponse> questions;

    public QuizResponse() {
    }

    public QuizResponse(Long id, String title, List<QuestionResponse> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public Long getId() { 
    	 return id; 
    	 }
    public void setId(Long id) { 
    	this.id = id;
    	}
    
    public String getTitle() {
    	return title; 
    	}
    public void setTitle(String title) { 
    	this.title = title; 
    	}
    
    public List<QuestionResponse> getQuestions() {
    	return questions; 
    	}
    public void setQuestions(List<QuestionResponse> questions) { 
    	this.questions = questions;
    	}
}