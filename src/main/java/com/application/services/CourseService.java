package com.application.services;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.model.Course;
import com.application.repository.CourseRepository;

@Service
public class CourseService 
{
	@Autowired
	private final CourseRepository courseRepo;
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
    public Course saveCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public Course addNewCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public List<Course> getAllCourses()
	{
		return (List<Course>)courseRepo.findAll();
	}
//
//	public void updateEnrolledcount(String coursename, int enrolledcount)
//	{
//		courseRepo.updateEnrolledcount(enrolledcount, coursename);
//	}
	
	public Course fetchCourseByCoursename(String courseName)
	{
		return courseRepo.findByCourseName(courseName);
	}
	
//	public Course fetchCourseByCourseid(String courseid)
//	{
//		return courseRepo.findByCourseid(courseid);
//	}
//
//	public List<Course> fetchByInstructorname(String instructorname)
//	{
//		return (List<Course>)courseRepo.findByInstructorname(instructorname);
//	}
	
//	public List<Course> fetchByInstructorinstitution(String instructorinstitution)
//	{
//		return (List<Course>)courseRepo.findByInstructorinstitution(instructorinstitution);
//	}
	
//	public List<Course> fetchByEnrolleddate(String enrolleddate)
//	{
//		return (List<Course>)courseRepo.findByEnrolleddate(enrolleddate);
//	}
//
//	public List<Course> fetchByCoursetype(String coursetype)
//	{
//		return (List<Course>)courseRepo.findByCoursetype(coursetype);
//	}
//
//	public List<Course> fetchByYoutubeurl(String youtubeurl)
//	{
//		return (List<Course>)courseRepo.findByYoutubeurl(youtubeurl);
//	}
//
//	public List<Course> fetchByWebsiteurl(String websiteurl)
//	{
//		return (List<Course>)courseRepo.findByWebsiteurl(websiteurl);
//	}
//
//	public List<Course> fetchBySkilllevel(String skilllevel)
//	{
//		return (List<Course>)courseRepo.findBySkilllevel(skilllevel);
//	}
	
//	public List<Course> fetchByLanguage(String language)
//	{
//		return (List<Course>)courseRepo.findByLanguage(language);
//	}
//
}