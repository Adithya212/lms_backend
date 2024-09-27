package com.application.service;

import com.application.model.Course;
import com.application.repository.CourseRepository;
import com.application.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test case for saveCourse (success case)
    @Test
    void testSaveCourse_Success() {
        Course course = new Course();
        course.setCourseName("Java Basics");

        when(courseRepo.save(any(Course.class))).thenReturn(course);

        Course savedCourse = courseService.saveCourse(course);

        assertNotNull(savedCourse);
        assertEquals("Java Basics", savedCourse.getCourseName());
        verify(courseRepo, times(1)).save(course);
    }

    // Test case for addNewCourse (success case)
    @Test
    void testAddNewCourse_Success() {
        Course course = new Course();
        course.setCourseName("Python for Beginners");

        when(courseRepo.save(any(Course.class))).thenReturn(course);

        Course newCourse = courseService.addNewCourse(course);

        assertNotNull(newCourse);
        assertEquals("Python for Beginners", newCourse.getCourseName());
        verify(courseRepo, times(1)).save(course);
    }

    // Test case for getAllCourses
    @Test
    void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setCourseName("Java Basics");

        Course course2 = new Course();
        course2.setCourseName("Advanced Java");

        courses.add(course1);
        courses.add(course2);

        when(courseRepo.findAll()).thenReturn(courses);

        List<Course> allCourses = courseService.getAllCourses();

        assertEquals(2, allCourses.size());
        assertEquals("Java Basics", allCourses.get(0).getCourseName());
        assertEquals("Advanced Java", allCourses.get(1).getCourseName());
        verify(courseRepo, times(1)).findAll();
    }

    // Test case for fetchCourseByCoursename (success case)
    @Test
    void testFetchCourseByCoursename_Success() {
        Course course = new Course();
        course.setCourseName("Java Basics");

        when(courseRepo.findByCourseName(anyString())).thenReturn(course);

        Course fetchedCourse = courseService.fetchCourseByCoursename("Java Basics");

        assertNotNull(fetchedCourse);
        assertEquals("Java Basics", fetchedCourse.getCourseName());
        verify(courseRepo, times(1)).findByCourseName("Java Basics");
    }

    // Test case for fetchCourseByCoursename (course not found)
    @Test
    void testFetchCourseByCoursename_NotFound() {
        when(courseRepo.findByCourseName(anyString())).thenReturn(null);

        Course fetchedCourse = courseService.fetchCourseByCoursename("Nonexistent Course");

        assertNull(fetchedCourse);
        verify(courseRepo, times(1)).findByCourseName("Nonexistent Course");
    }
}

