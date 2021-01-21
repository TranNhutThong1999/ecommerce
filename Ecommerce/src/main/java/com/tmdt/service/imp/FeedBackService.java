package com.tmdt.service.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tmdt.converter.FeedbackConverter;
import com.tmdt.dto.FeedBackDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.dto.StarDTO;
import com.tmdt.entity.FeedBack;
import com.tmdt.entity.Star;
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
		public boolean save(String content, int idPost, int s) {
			// TODO Auto-generated method stub
			FeedBackDTO f = new FeedBackDTO();
			f.setContent(content);
			
			
			f.setIdPost(idPost);
			
			StarDTO st = new StarDTO();
			st.setValue(s);
			f.setStar(st);
			
			FeedBack b = feedbackConverter.toEntity(f);
			
			b.setTime( new Timestamp(new Date().getTime()));
			if(feedbackRepository.findOneByUser_IdAndPost_Id(b.getUser().getId(), b.getPost().getId()).orElse(null) != null)
				return false;
			
			feedbackRepository.save(b);
			return true;
		}

		@Override
		public Page<FeedBackDTO> findAll(Map<String, String> q) {
			// TODO Auto-generated method stub
			int pageNumber = Integer.valueOf(q.get("pageNumber")) - 1;
			int pageSize = Integer.valueOf(q.get("pageSize"));
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			return feedbackRepository.findAll(pageable).map(feedbackConverter::toDTO);
		}

		@Override
		public void delete(int id) {
			// TODO Auto-generated method stub
			feedbackRepository.deleteById(id);
		}
		
}
