package com.application.controller;

import com.application.dto.FeedBackRequest;
import com.application.model.Feedback;
import com.application.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedBackRequest feedBackRequest) {
        Feedback feedback = feedbackService.createFeedback(feedBackRequest);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping()
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getFeedback();
        return ResponseEntity.ok(feedbacks);
    }
}