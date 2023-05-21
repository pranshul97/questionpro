package com.questionpro.assessment.service;

import java.util.List;
import java.util.Optional;

import com.questionpro.assessment.DTO.CommentsDTO;

public interface CommentsService {

	Optional<List<CommentsDTO>> fetchComments(int itemNumber);
}
