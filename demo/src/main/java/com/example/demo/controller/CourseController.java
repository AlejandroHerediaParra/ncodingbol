package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dataTransferObeject.CourseDto;
import com.example.demo.domain.Course;
import com.example.demo.repository.CourseRepository;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<Course> findAllUsers() {
		return (List<Course>) courseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findUserById(@PathVariable(value = "id") Long id) {	
		return ResponseEntity.status(HttpStatus.OK).body(courseRepository.findById(id).get());
	}
	
	@PostMapping
    public Course saveUser(@Validated @RequestBody CourseDto course) {
        Course newCourse = new Course();
        newCourse.setDescription(course.getDescription());
        newCourse.setDuration(course.getDuration());
        newCourse.setName(course.getName());
        newCourse.setRate(course.getRate());
        newCourse.setTag(course.getTag());
        newCourse.setVotes(course.getVotes());
		courseRepository.save(newCourse);
        return newCourse;
    }
}
