package com.questionpro.assessment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assessment.DTO.TopStoryDTO;
import com.questionpro.assessment.service.TopStoriesService;

@RestController
@CrossOrigin
public class TopStoriesControllerImpl implements TopStoriesController {

	@Autowired
	private TopStoriesService topStoriesService;
	
	//Controller method to fetch top 10 stories fetched from hacker api
	@GetMapping("/top-stories")
	@Override
	@Cacheable(value = "topStoriesCache")
	public ResponseEntity<Object> topStories() {
		// TODO Auto-generated method stub
		Optional<List<TopStoryDTO>> resultData = topStoriesService.topStoriesSearch();
		
		ResponseEntity<Object> response = null;
		
		if (resultData.isPresent()) {
			response = new ResponseEntity<Object>(resultData, HttpStatusCode.valueOf(200));
		} else {
			response = new ResponseEntity<Object>(null, HttpStatusCode.valueOf(300));
		}
		
		return response;
	}

}
