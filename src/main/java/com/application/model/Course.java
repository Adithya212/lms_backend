package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private String description;
	private String videoUrl;

}