package com.questionpro.assessment.service;

import java.util.List;
import java.util.Optional;

import com.questionpro.assessment.entity.StoryData;

public interface PastStoriesService {

	Optional<List<StoryData>> fetchPastStories(); 
}
