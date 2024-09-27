package com.application.controller;

import com.application.model.Course;
import com.application.model.Enrollment;
import com.application.dto.EnrollmentRequest;
import com.application.model.Employee;
import com.application.repository.CourseRepository;
import com.application.repository.EnrollmentRepository;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:4200")
public class EnrollmentController extends EnrollmentRequest{

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok( enrollmentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
        Course course = courseRepository.findById(enrollmentRequest.getCourseId()).orElseThrow();
        Employee user = userRepository.findById(enrollmentRequest.getId()).orElseThrow();

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setStartDate(LocalDateTime.now());
        enrollment.setStatus("Enrolled");
        enrollment.setCompletionDate(enrollment.getStartDate().plusHours(48));

        return ResponseEntity.ok(enrollmentRepository.save(enrollment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return ResponseEntity.ok(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Integer id, @RequestBody Enrollment enrollmentDetails) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus(enrollmentDetails.getStatus());
        enrollment.setProgress(enrollmentDetails.getProgress());
        enrollment.setCompletionDate(enrollmentDetails.getCompletionDate());

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
        return ResponseEntity.ok().build();
    }

}
