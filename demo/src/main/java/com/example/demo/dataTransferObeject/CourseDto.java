package com.example.demo.dataTransferObeject;


import lombok.Data;

@Data
public class CourseDto {
	private String name;
	private String tag;
	private String description;
	private String duration;
	private Float rate;
	private int votes;
}
