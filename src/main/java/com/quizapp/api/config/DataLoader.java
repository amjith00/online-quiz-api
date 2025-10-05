package com.quizapp.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.quizapp.api.model.Question;
import com.quizapp.api.model.QuestionType;
import com.quizapp.api.model.Quiz;
import com.quizapp.api.repository.QuizRepository;
import com.quizapp.api.repository.QuestionRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setTitle("Java Programming Quiz");
        Quiz savedQuiz = quizRepository.save(quiz);

  //Q1
        Question q1 = new Question();
        q1.setText("What is the default value of a boolean in Java?");
        q1.setType(QuestionType.SINGLE_CHOICE);
        q1.setOptionA("true");
        q1.setOptionB("false");
        q1.setOptionC("null");
        q1.setOptionD("0");
        q1.setCorrectAnswer("B");
        q1.setQuiz(savedQuiz);
        questionRepository.save(q1);
//Q2
        Question q2 = new Question();
        q2.setText("Which keyword is used to inherit a class in Java?");
        q2.setType(QuestionType.SINGLE_CHOICE);
        q2.setOptionA("implements");
        q2.setOptionB("extends");
        q2.setOptionC("inherits");
        q2.setOptionD("super");
        q2.setCorrectAnswer("B");
        q2.setQuiz(savedQuiz);
        questionRepository.save(q2);
//Q3
        Question q3 = new Question();
        q3.setText("Which of the following are Java access modifiers?");
        q3.setType(QuestionType.MULTIPLE_CHOICE);
        q3.setOptionA("public");
        q3.setOptionB("private");
        q3.setOptionC("protected");
        q3.setOptionD("package");
        q3.setCorrectAnswer("A,B,C");
        q3.setQuiz(savedQuiz);
        questionRepository.save(q3);
//Q4
        Question q4 = new Question();
        q4.setText("Which of the following are primitive data types in Java?");
        q4.setType(QuestionType.MULTIPLE_CHOICE);
        q4.setOptionA("int");
        q4.setOptionB("String");
        q4.setOptionC("boolean");
        q4.setOptionD("float");
        q4.setCorrectAnswer("A,C,D");
        q4.setQuiz(savedQuiz);
        questionRepository.save(q4);
//Q5
        Question q5 = new Question();
        q5.setText("What is the size of an int in Java?");
        q5.setType(QuestionType.SINGLE_CHOICE);
        q5.setOptionA("16 bits");
        q5.setOptionB("32 bits");
        q5.setOptionC("64 bits");
        q5.setOptionD("Depends on the platform");
        q5.setCorrectAnswer("B");
        q5.setQuiz(savedQuiz);
        questionRepository.save(q5);

        System.out.println("questions loaded successfully!");
    }
}