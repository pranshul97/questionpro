package com.questionpro.assessment.controller;

import org.springframework.http.ResponseEntity;

public interface CommentsController {

	ResponseEntity<Object> comments(int storyNumber);
}
