package com.questionpro.assessment.service;

import java.util.List;
import java.util.Optional;

public interface TopStoriesService {

	Optional<List<Long>> topStoriesSearch();
}
