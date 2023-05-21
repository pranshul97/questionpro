package com.questionpro.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.questionpro.assessment.QuestionproApplication;
import com.questionpro.assessment.DTO.CommentsDTO;
import com.questionpro.assessment.VO.Constants;
import com.questionpro.assessment.VO.Item;

import ch.qos.logback.classic.Logger;

@Service
public class CommentsServiceImpl implements CommentsService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	@Value("${basePathURL}")
	private String basePathURL;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Optional<List<CommentsDTO>> fetchComments(int itemNumber) {
		// TODO Auto-generated method stub
		List<CommentsDTO> topComments = null;
		try {
			logger.info("Inside fetchComment method to fetch itemData");
			
			Optional<Item> item = itemSearch(itemNumber);
			
			if (item.isEmpty()) {
				logger.info("No data received for %s item from search item api so returning empty list from fetchComments service");
				return Optional.empty();
			} else {
				topComments = processCommentsBasedOnChildComments(item.get());
				
				if(topComments.size()>0) {
					logger.info("Execution completed for fetch comments");
					return Optional.of(topComments);
				}
				logger.info("Unable to fetch comments for mentioned item");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.warn("Exception encountered inside fetchComments method");
		}
		
		return Optional.empty();
	}
	
	private Optional<Item> itemSearch(int itemNumber){
		try {
			Item item = null;
			ResponseEntity<Item> response = restTemplate.getForEntity(basePathURL + "/item/" 
					+ itemNumber + Constants.prettyPrintContext, Item.class);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				item = response.getBody();
				
				if(item != null) {
					return Optional.of(item);
				}
				else {
					logger.warn("No data received for item number %s by item Search api" , itemNumber);
				}
			} else {
				logger.warn("Item Api returned non 200 status, please check api. Code resturned by api %s", response.getStatusCode());
			}
		} catch (Exception e) {
			logger.warn("Exception received in itemSearch method, please check %s", e.getMessage());
		}
		
		return Optional.empty();
	}
	
	private List<CommentsDTO> processCommentsBasedOnChildComments(Item item){
		logger.info("Inside method processCommentsBasedOnChildComments method");
		List<Integer> commentIds = item.getKids();
		List<Item> commentsList = new ArrayList<>();
		List<CommentsDTO> commentsReturnList = new ArrayList<>();
		try {
			for ( int commentId : commentIds) {
				Optional<Item> commentData = itemSearch(commentId);
				if (commentData.isPresent()) {
					logger.info("Data received for commentId %s ", commentId);
					commentsList.add(commentData.get());
				}
				else {
					logger.info("No data received for commentId %s ", commentId);
				}
			}
			logger.info("Sorting items data based on number of comments in reverse order");
			commentsList.sort((i1, i2) -> i2.getKids().size()-i1.getKids().size());
			
			//commentsList.forEach(i -> System.out.println("Itemid = " + i.getId() + " & Number of comments = " + i.getKids().size()));
			
			for ( Item itemIt : commentsList ) {
				if ( commentsReturnList.size()>=10)
					break;
				else {
					logger.info("Mapping items data to comments DTO");
					CommentsDTO comment = new CommentsDTO();
					comment.setCommentText(itemIt.getText());
					comment.setId(itemIt.getId());
					comment.sethandleName(itemIt.getBy());
					
					commentsReturnList.add(comment);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.warn("Exception received in processCommentsBasedOnChildComments method %", e.getMessage());
		}
		logger.info("Execution completed in processCommentsBasedOnChildComments method");
		return commentsReturnList;
		
	}

}
