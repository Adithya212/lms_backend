package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String courseId;
	private String courseName;
	private String category;
	private String enrolledDate;
	private String features;
//	private String instructorname;
//	private String enrolledcount;//initially 0. should increment
//	private String youtubeurl;
//	private String websiteurl;
//	private String coursetype;
//	private String skilllevel;
	private String description;
	private String videoUrl;
//	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//	private List<Enrollment> enrollments;
}