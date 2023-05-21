package com.questionpro.assessment.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.assessment.QuestionproApplication;
import com.questionpro.assessment.DTO.CommentsDTO;
import com.questionpro.assessment.service.CommentsService;

import ch.qos.logback.classic.Logger;

@RestController
public class CommentsControllerImpl implements CommentsController{

	@Autowired
	private CommentsService commentsService;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	//controller for comments api which returns top 10 comments based on number of child comments on each
	@GetMapping("/comments")
	@Override
	public ResponseEntity<Object> comments(int itemNumber){
		logger.info("/comments api invoked for itemNumber %s", itemNumber);
		Optional<List<CommentsDTO>> topComments = commentsService.fetchComments(itemNumber);
		ResponseEntity<Object> response = null;
		
		if (topComments.isPresent() && topComments.get().size()>0) {
			response = new ResponseEntity<Object>(topComments, HttpStatusCode.valueOf(200));
		} else {
			response = new ResponseEntity<Object>("Invalid item id or comments doesn't exist in story", HttpStatusCode.valueOf(300));
		}
		logger.info("Working completed for /comments api");
		return response;
	}
}
