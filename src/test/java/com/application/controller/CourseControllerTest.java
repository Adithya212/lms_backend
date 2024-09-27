package com.application.controller;

import com.application.model.Course;
import com.application.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void testAddNewCourse() throws Exception {
        Course course = new Course();
        course.setCourseName("Spring Boot");

        when(courseService.addNewCourse(any(Course.class))).thenReturn(course);

        String requestJson = objectMapper.writeValueAsString(course);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Spring Boot"));
    }

    @Test
    void testGetAllCourses() throws Exception {
        Course course = new Course();
        course.setCourseName("Spring Boot");

        when(courseService.getAllCourses()).thenReturn(Collections.singletonList(course));

        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseName").value("Spring Boot"));
    }
}
