package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {	
		return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).get());
	}
	
	@PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        userRepository.save(user);
        return user;
    }
	
	@PatchMapping("/add_course/{id}")
	public ResponseEntity<?> addCourse(@PathVariable(value = "id") Long id, @Validated @RequestBody Course course) {
		//needs verification if 
		User user = userRepository.findById(id).get();
		if (user == null || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return ResponseEntity.notFound().build();
		} else {
			if (user.getCourses().contains(course)) {
				return ResponseEntity.badRequest().build();
			}
			user.addCourse(course);
			userRepository.save(user);
			return ResponseEntity.ok(user);
		}
	}
	
	@PatchMapping("/remove_course/{id}")
	public ResponseEntity<?> removeCourse(@PathVariable(value = "id") Long id, @Validated @RequestBody Course course) {
		//needs verification if 
		User user = userRepository.findById(id).get();
		if (user == null || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return ResponseEntity.notFound().build();
		} else {
			user.removeCourse(course.getName());
			userRepository.save(user);
			return ResponseEntity.ok(user);
		}
	}
}
