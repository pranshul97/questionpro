package com.questionpro.assessment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.questionpro.assessment.DTO.CommentsDTO;
import com.questionpro.assessment.entity.StoryData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PastStoryDataImpl implements PastStoryData {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@Transactional
	public void saveStoryData(com.questionpro.assessment.entity.StoryData storyData) {
		// TODO Auto-generated method stub
		entityManager.merge(storyData);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StoryData> findAll(){
		return entityManager.createQuery("Select s from StoryData s").getResultList();
	}

}
