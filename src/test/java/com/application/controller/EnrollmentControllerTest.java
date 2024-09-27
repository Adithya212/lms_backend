package com.application.controller;

import com.application.dto.EnrollmentRequest;
import com.application.model.Course;
import com.application.model.Employee;
import com.application.model.Enrollment;
import com.application.repository.CourseRepository;
import com.application.repository.EnrollmentRepository;
import com.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EnrollmentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(enrollmentController).build();
    }

    @Test
    void testCreateEnrollment() throws Exception {
        EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
        enrollmentRequest.setCourseId(1);
        enrollmentRequest.setId(1);
        String requestJson = objectMapper.writeValueAsString(enrollmentRequest);

        Course course = new Course();
        course.setCourseName("Spring Boot");

        Employee user = new Employee();
        user.setUserName("John Doe");

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        enrollment.setStartDate(LocalDateTime.now());
        enrollment.setStatus("Enrolled");

        when(courseRepository.findById(1)).thenReturn(java.util.Optional.of(course));
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Enrolled"))
                .andExpect(jsonPath("$.course.courseName").value("Spring Boot"))
                .andExpect(jsonPath("$.user.userName").value("John Doe"));
    }
}
