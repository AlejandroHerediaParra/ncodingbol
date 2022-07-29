package com.example.demo.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String Country;
	@ManyToMany
	private Set<Course> courses = new HashSet<Course>();
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(String courseName) {
		Course course = this.courses.stream().filter(
				co -> co.getName().matches(courseName))
				.findFirst()
				.orElse(null);
		if (course != null) {
			this.courses.remove(course);
			course.getUsers().remove(this);
		}
	}
}
