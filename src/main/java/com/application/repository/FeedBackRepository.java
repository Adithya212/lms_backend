package com.application.repository;


import com.application.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.enrollment.course.id = :courseId")
    List<Feedback> findByCourseId(@Param("courseId") int courseId);
}
