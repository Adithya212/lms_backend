package com.application.controller;

import com.application.model.Course;
import com.application.model.Enrollment;
import com.application.model.EnrollmentRequest;
import com.application.model.User;
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
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
        Course course = courseRepository.findById(enrollmentRequest.getCourseId()).orElseThrow();
        User user = userRepository.findById(enrollmentRequest.getEmail()).orElseThrow();

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setStartDate(LocalDateTime.now());
        enrollment.setStatus("Enrolled");

        return enrollmentRepository.save(enrollment);
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

//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @GetMapping
//    public List<Enrollment> getAllEnrollments() {
//        return enrollmentRepository.findAll();
//    }
//
//    @PostMapping
//    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
//        return enrollmentRepository.save(enrollment);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
//        Enrollment enrollment = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//        return ResponseEntity.ok(enrollment);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollmentDetails) {
//        Enrollment enrollment = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//
//        enrollment.setStatus(enrollmentDetails.getStatus());
//        enrollment.setProgress(enrollmentDetails.getProgress());
//        enrollment.setCompletionDate(enrollmentDetails.getCompletionDate());
//
//        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
//        return ResponseEntity.ok(updatedEnrollment);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
//        Enrollment enrollment = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//
//        enrollmentRepository.delete(enrollment);
//        return ResponseEntity.ok().build();
//    }
//}

//    @GetMapping("/course/{courseId}")
//    public ResponseEntity<Enrollment> getEnrollmentsByCourse(@PathVariable String courseId) {
//        Enrollment enrollments = enrollmentRepository.findByCourseId(courseId);
//        return ResponseEntity.ok(enrollments);
//    }
}
