package com.questionpro.assessment.service;

import java.util.List;
import java.util.Optional;

import com.questionpro.assessment.DTO.TopStoryDTO;

public interface TopStoriesService {

	Optional<List<TopStoryDTO>> topStoriesSearch();
}
