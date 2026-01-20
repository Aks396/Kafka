package com.akash.test.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.test.producer.model.Course;
import com.akash.test.producer.service.KafkaService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;

	@PostMapping("add-course")
	public ResponseEntity<String> addCourse(@RequestBody Course course) {
		String response = kafkaService.sendMessage(course);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("get-course")
	public ResponseEntity<String> getCourse() {
		String response = kafkaService.getMessage();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
