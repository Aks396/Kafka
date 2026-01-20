package com.akash.test.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.akash.test.producer.model.Course;

@Service
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, Course> kafkaTemplate;

	private String message;

	public String sendMessage(Course course) {

		kafkaTemplate.send("akash", "course", course);
		return "Course message sent to kafka";
	}

	@KafkaListener(topics = "akash", groupId = "akash-group")
	public void consume(Course course) {
		message = course + " Got the data from kafka";
		System.out.println(message);

	}

	public String getMessage() {
		return message;
	}

}
