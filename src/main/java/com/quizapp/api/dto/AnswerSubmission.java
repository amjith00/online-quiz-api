package com.quizapp.api.dto;

import java.util.List;

public class AnswerSubmission {
    private List<Answer> answers;
	public AnswerSubmission(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
    
    public static class Answer {
        private Long questionId;
        private String selectedOption; 
		public Answer(Long questionId, String selectedOption) {
			this.questionId = questionId;
			this.selectedOption = selectedOption;
		}
		public Long getQuestionId() {
			return questionId;
		}
		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}
		public String getSelectedOption() {
			return selectedOption;
		}
		public void setSelectedOption(String selectedOption) {
			this.selectedOption = selectedOption;
		}
        
    }


    
    
}
