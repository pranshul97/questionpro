package com.questionpro.assessment.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionpro.assessment.QuestionproApplication;
import com.questionpro.assessment.entity.StoryData;
import com.questionpro.assessment.repository.PastStoryData;

import ch.qos.logback.classic.Logger;

@Service
public class PastStoriesServiceImpl implements PastStoriesService {

	@Autowired
	private PastStoryData pastStoryDataRepo;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	@Override
	public Optional<List<StoryData>> fetchPastStories() {
		logger.info("Inside fetchPastStories method to get all data");
		try {
			List<StoryData> pastStoryData = pastStoryDataRepo.findAll();
			if ( (pastStoryData != null) && pastStoryData.size() >0 ) {
				logger.info("Got data from DB");
				
				return Optional.of(pastStoryData);
			}
		} catch (Exception e) {
			logger.info("Exception occured inside fetchPastStories method %s", e.getMessage());
		}
		
		return Optional.empty();
	}

}
