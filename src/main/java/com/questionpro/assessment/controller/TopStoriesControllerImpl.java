package com.questionpro.assessment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assessment.service.TopStoriesService;

@RestController
@CrossOrigin
public class TopStoriesControllerImpl implements TopStoriesController {

	@Autowired
	private TopStoriesService topStoriesService;
	
	@GetMapping("/top-stories")
	@Override
	public ResponseEntity<Object> topStories() {
		// TODO Auto-generated method stub
		Optional<List<Long>> resultData = topStoriesService.topStoriesSearch();
		
		ResponseEntity<Object> response = new ResponseEntity<Object>(resultData, HttpStatusCode.valueOf(200));
		return response;
	}

}
