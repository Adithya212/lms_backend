package com.application.repository;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.application.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>
{
    List<Enrollment> findByCourse_Id(Integer courseId);
    List<Enrollment> findByUser_Id(int userId);  // Custom query method
}

//    public Enrollment findByCoursename(String coursename);
//
//	public Enrollment findByCourseId(String courseid);
	
//    public List<Enrollment> findByEnrolledusername(String enrolledusername);
//
//	public List<Enrollment> findByEnrolleduserid(String enrolleduserid);
//
//	public List<Enrollment> findByEnrolledusertype(String enrolledusertype);
//
//	public List<Enrollment> findByInstructorname(String instructorname);
//
//	public List<Enrollment> findByInstructorinstitution(String instructorinstitution);
//
//    public List<Enrollment> findByEnrolleddate(String enrolleddate);
//
//	public List<Enrollment> findByCoursetype(String coursetype);
//
//	public List<Enrollment> findByYoutubeurl(String youtubeurl);
//
//	public List<Enrollment> findByWebsiteurl(String websiteurl);
//
//    public List<Enrollment> findBySkilllevel(String skilllevel);
//
//	public List<Enrollment> findByLanguage(String language);
//
//	@Transactional
//	@Modifying
//	@Query(value = "update enrollment set enrolledcount = ?1 where coursename = ?2",nativeQuery = true)
//	public void updateEnrolledcount(int enrolledcount, String coursename);

