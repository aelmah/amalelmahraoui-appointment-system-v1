package com.example.feedbacksservice.repository;

import com.example.feedbacksservice.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
