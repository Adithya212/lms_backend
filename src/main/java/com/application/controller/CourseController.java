package com.application.controller;
import com.application.model.Course;
import com.application.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
//    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.addNewCourse(course));
    }
    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }



}
