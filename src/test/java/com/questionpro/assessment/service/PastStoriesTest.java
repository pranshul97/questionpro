package com.questionpro.assessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.questionpro.assessment.entity.StoryData;
import com.questionpro.assessment.repository.PastStoryData;

@SpringBootTest
public class PastStoriesTest {

	@MockBean
	PastStoryData pastStoryDataRepo;
	
	@Autowired
	PastStoriesService pastStoryService;
	
	@Test
	public void fetchPastStoriesTest() {
		StoryData storydata = new StoryData();
		storydata.setId(123);
		storydata.setScore(1234);
		storydata.setTime(1234);
		storydata.setTitle("Abc");
		storydata.setURL("url");
		storydata.setUser("user");
		
		List<StoryData> listStory = new ArrayList<>();
		listStory.add(storydata);
		
		Mockito.when(pastStoryDataRepo.findAll()).thenReturn(listStory);
		
		Optional<List<StoryData>> response = pastStoryService.fetchPastStories();
		
		assertNotNull(response);
		assertTrue(response.isPresent());
		assertEquals(response.get().size(), 1);
	}
	
}
