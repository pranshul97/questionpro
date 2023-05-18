package com.questionpro.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.questionpro.assessment.QuestionproApplication;
import com.questionpro.assessment.DTO.TopStoryDTO;
import com.questionpro.assessment.VO.Constants;
import com.questionpro.assessment.VO.Item;

import ch.qos.logback.classic.Logger;

@Service
public class TopStoriesServiceImpl implements TopStoriesService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	@Value("${basePathURL}")
	private String basePathURL;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public Optional<List<Long>> topStoriesSearch() {
		// TODO Auto-generated method stub
		
		Optional<List<Long>> result = searchBaseApi();
		
		

		
		return result.isEmpty()?Optional.empty():result;
	}
	
	@SuppressWarnings("unchecked")
	private Optional<List<Long>> searchBaseApi() {
		List<Long> result = null;
		logger.info("Inside searchBaseApi method");
		try {
			result = new ArrayList<>();
			ResponseEntity<List> topStoriesResponse = restTemplate.getForEntity(basePathURL + Constants.topStoriesContext, List.class);
			
			if (topStoriesResponse.getStatusCode().is2xxSuccessful()) {
				logger.info("Top Search api call successful");
				result = topStoriesResponse.getBody();
				if(!result.isEmpty()) {
					logger.info("Data Retrieved");
				}
				else {
					logger.warn("Data not received, please check api call");
				}
			}
			else {
				logger.warn("Top stories api call failing");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception received in searchBaseApi function %s", e.getMessage());
		}
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result);
	}
	
	private Optional<List<TopStoryDTO>> searchStoriesData(List<Long> result){
		
		
		return Optional.empty();
		
	}
	
	private Optional<TopStoryDTO> itemSearch(long itemNo) {
		TopStoryDTO topStory = null;
		Item item = null;
		try {
			logger.info("Inside ItemSearch function to retrieve data of item api");
			ResponseEntity<Item> response = restTemplate.getForEntity(basePathURL + "/item/" 
		+ itemNo + Constants.prettyPrintContext, Item.class);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				item = response.getBody();
				if(item != null) {
					topStory = new TopStoryDTO();
					topStory.setScore(item.getScore());
					topStory.setTime(item.getTime());
					topStory.setTitle(item.getTitle());
					topStory.setURL(item.getUrl());
					topStory.setUser(item.getBy());
				}
				else {
					logger.warn("No data received for item number %s by item Search api" , itemNo);
				}
			} else {
				logger.warn("Item Api returned non 200 status, please check call. Code resturned by api %s", response.getStatusCode());
			}
			
		} catch (Exception e) {
			logger.error("Exception in itemSearch function %s", e.getMessage());
		}
		return (topStory != null)?Optional.of(topStory):Optional.empty();
	}

}
