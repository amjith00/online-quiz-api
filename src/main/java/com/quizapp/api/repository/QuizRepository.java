package com.quizapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quizapp.api.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // Custom query methods can be added here if needed
}
