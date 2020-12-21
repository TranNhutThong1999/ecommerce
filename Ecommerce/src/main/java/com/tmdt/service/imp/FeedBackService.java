package com.tmdt.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmdt.converter.FeedbackConverter;
import com.tmdt.dto.FeedBackDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.repository.FeedbackRepository;
import com.tmdt.service.IFeedbackService;

@Service
public class FeedBackService implements IFeedbackService {
		@Autowired
		private FeedbackRepository feedbackRepository;

		@Autowired
		private FeedbackConverter feedbackConverter;
		
		public void save(FeedBackDTO f) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void save(String content, int idPost) {
			// TODO Auto-generated method stub
			FeedBackDTO f = new FeedBackDTO();
			f.setContent(content);
			
			PostDTO p = new PostDTO();
			p.setId(idPost);
			
			f.setPost(p);
			feedbackRepository.save(feedbackConverter.toEntity(f));
		}
		
}
