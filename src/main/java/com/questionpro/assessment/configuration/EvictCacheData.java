package com.questionpro.assessment.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.questionpro.assessment.QuestionproApplication;

import ch.qos.logback.classic.Logger;

@Component
public class EvictCacheData {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestionproApplication.class);
	
	@CacheEvict(value = "topStoriesCache", allEntries = true)
	@Scheduled(fixedRateString = "${topStoriesCache.ttl}")
	public void deleteTopStoriesDataCache() {
		logger.info("Evicting cache as TTL has been reached");
	}
}
