package com.application.services;

import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.model.Enrollment;
import com.application.repository.EnrollmentRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class EnrollmentService 
{
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	public Enrollment saveEnrollment(Enrollment enrollment)
	{
		return enrollmentRepository.save(enrollment);
	}
	
	public Enrollment addNewEnrollment(Enrollment enrollment)
	{
		return enrollmentRepository.save(enrollment);
	}

	public List<Enrollment> getEnrollmentsByUserId(int userId) {
		return enrollmentRepository.findByUser_Id(userId);
	}

}