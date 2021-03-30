package com.exadel.team2.sandbox.dao;

import org.springframework.stereotype.Repository;
import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewFeedbackDAO extends JpaRepository<InterviewFeedbackEntity, Long> {
}
