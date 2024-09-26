package com.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Enrollment
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Employee user;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private String status;
	private Float progress;
	private LocalDateTime startDate;
	private LocalDateTime completionDate;

	public Enrollment() {
		this.status = "Enrolled";
		this.progress = 0.0f;
		this.startDate = LocalDateTime.now();
	}
}
