package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Enrollment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private String status;
	private Float progress;
	private LocalDateTime startDate;
	private LocalDateTime completionDate;

	public Enrollment() {
		// Set default values for status and progress
		this.status = "Enrolled";
		this.progress = 0.0f;
		this.startDate = LocalDateTime.now();
	}
//

}

//	private String coursename;
//	private String courseid;
//	private String enrolleddate;
//	private String enrolledusername;
//	private String enrolleduserid;
//	private String enrolledusertype;
//	private String instructorname;
//	private String instructorinstitution;
//	private String enrolledcount;
//	private String youtubeurl;
//	private String websiteurl;
//	private String coursetype;
//	private String skilllevel;
//	private String language;
//	private String description;