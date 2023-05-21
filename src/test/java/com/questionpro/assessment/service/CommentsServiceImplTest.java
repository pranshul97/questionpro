package com.questionpro.assessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.questionpro.assessment.DTO.CommentsDTO;
import com.questionpro.assessment.VO.Item;

@SpringBootTest
public class CommentsServiceImplTest {

	@MockBean
	RestTemplate restTemplate;
	
	@Autowired
	CommentsService commentService;
	
	@Test
	public void fetchCommentsTest() {
		Item item = new Item();
		item.setBy("Abc");
		item.setId(1);
		item.setScore(123);
		item.setKids(new ArrayList<>(Arrays.asList(1,2,3)));
		item.setText("ABC");
		item.setTime(1234);
		
		ResponseEntity<Item> response1 = new ResponseEntity<Item>(item, HttpStatusCode.valueOf(200));
		
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), (Class<Item>) Mockito.any(Object.class))).thenReturn(response1);
		
		Optional<List<CommentsDTO>> list = commentService.fetchComments(123);
		
		assertNotNull(list);
		assertTrue(list.isPresent());
		assertEquals(list.get().size(), 3);
	}
}
