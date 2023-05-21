package com.questionpro.assessment.repository;

import java.util.List;

import com.questionpro.assessment.entity.StoryData;

public interface PastStoryData {

	void saveStoryData(com.questionpro.assessment.entity.StoryData storyData);
	
	List<StoryData> findAll();
}
