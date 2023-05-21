package com.questionpro.assessment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assessment.entity.StoryData;
import com.questionpro.assessment.service.PastStoriesService;

@RestController
public class PastStoriesControllerImpl implements PastStoriesController {

	@Autowired
	private PastStoriesService pastStoryService;
	
	//Controller method to fetch all stories shared in the past by /top-stories api
	@SuppressWarnings("unchecked")
	@GetMapping("/past-stories")
	@Override
	public ResponseEntity<Object> pastStories() {
		// TODO Auto-generated method stub
		ResponseEntity<Object> response = null;
		Optional<List<StoryData>> pastStoryData = pastStoryService.fetchPastStories();
		
		if(pastStoryData.isPresent() && pastStoryData.get() != null && pastStoryData.get().size()>0) {
			response = new ResponseEntity(pastStoryData.get(), HttpStatusCode.valueOf(200));
			return response;
		}
		response = new ResponseEntity("No data found in data", HttpStatusCode.valueOf(300));
		
		return response;
		
	}

}
