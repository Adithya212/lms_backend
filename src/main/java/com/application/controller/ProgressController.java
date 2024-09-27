package com.application.controller;

import com.application.model.Progress;
import com.application.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Endpoint to update user progress
    @PostMapping("/update")
    public ResponseEntity<Progress> updateProgress(@RequestBody Progress progress) {
        Progress updatedProgress = progressService.updateProgress(progress);
        return ResponseEntity.ok(updatedProgress);
    }

    // Endpoint to fetch progress by course ID
//    @GetMapping("/course/{courseId}")
//    public List<Progress> getProgressByCourse(@PathVariable Long courseId) {
//        return progressService.getProgressByCourse(courseId);
//    }
}
