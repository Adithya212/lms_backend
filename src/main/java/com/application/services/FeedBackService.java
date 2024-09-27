package com.application.services;


import com.application.dto.FeedBackRequest;
import com.application.model.Feedback;
import com.application.model.Enrollment;
import com.application.repository.FeedBackRepository;
import com.application.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {

    @Autowired
    private FeedBackRepository feedbackRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Feedback createFeedback(FeedBackRequest feedBackRequest) {
        Enrollment enrollment = enrollmentRepository.findById(feedBackRequest.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Feedback feedback = new Feedback();
        feedback.setEnrollment(enrollment);
        feedback.setComment(feedBackRequest.getComment());
        feedback.setRating(feedBackRequest.getRating());

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedback() {
        return feedbackRepository.findAll();
    }
}

