package com.application.controller;

import com.application.model.Enrollment;
import com.application.model.Progress;
import com.application.repository.EnrollmentRepository;
import com.application.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Update progress based on user watching the video
    @PutMapping("/update/{enrollmentId}")
    public ResponseEntity<Enrollment> updateProgress(@PathVariable Integer enrollmentId, @RequestBody Float progress) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOpt.isPresent()) {
            Enrollment enrollment = enrollmentOpt.get();
            enrollment.setProgress(progress);
            enrollmentRepository.save(enrollment);
            return ResponseEntity.ok(enrollment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Fetch progress for the admin dashboard
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getProgressByCourse(@PathVariable Integer courseId) {
        return enrollmentRepository.findByCourse_Id(courseId);
    }
}
//
//    @Autowired
//    private ProgressService progressService;
//    @PostMapping
//    public ResponseEntity<Progress> updateProgress(@RequestBody Progress progress) {
//        Progress updatedProgress = progressService.updateProgress(progress);
//        return ResponseEntity.ok(updatedProgress);
//    }
//    @GetMapping
//    public List<Progress> getprogress(){
//        return progressService.getProgress();
//
//    }
//


