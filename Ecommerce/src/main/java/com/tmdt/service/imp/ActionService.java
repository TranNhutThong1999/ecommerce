package com.tmdt.service.imp;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tmdt.converter.ActionConverter;
import com.tmdt.converter.PostConverter;
import com.tmdt.dto.ActionDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.entity.Action;
import com.tmdt.entity.StatePost;
import com.tmdt.entity.User;
import com.tmdt.repository.ActionRepository;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IActionService;

@Service
public class ActionService implements IActionService {
	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private ActionConverter actionConverter;

	@Autowired
	private CustomUserDetail customUserDetail;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostConverter postConverter;
	
	@Override
	public void save(ActionDTO a) {
		// TODO Auto-generated method stub
		Action ad = actionConverter.toEntity(a);
		Action action = actionRepository.findOneByUser_IdAndPost_Id(ad.getUser().getId(), ad.getPost().getId())
				.orElse(null);
		if (action != null) {
			actionRepository.delete(action);
			return;
		}
		actionRepository.save(ad);
	}

	@Override
	public Page<PostDTO> findAllByUser_IdAndPost_State(Map<String, String> q) {
		int pageNumber = Integer.valueOf(q.get("pageNumber")) - 1;
		int pageSize = Integer.valueOf(q.get("pageSize"));
		int idUser = Integer.valueOf(q.get("idUser"));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Action> action = actionRepository.findAllByUser_IdAndPost_State(pageable, idUser, StatePost.Approved);
		return  action.map(e-> e.getPost()).map(postConverter::toDTO);
	}

	@Override
	public List<Integer> findListLike() {
		// TODO Auto-generated method stub
		List<Integer> in = null;
		User u = userRepository.findOneByUserName(customUserDetail.getPrinciple().getName()).orElse(null);
		List<Action> list = actionRepository.findByUser_Id(u.getId());
		if (list == null) {
			return in;
		}
		return list.stream().map(e -> e.getPost().getId()).collect(Collectors.toList());
	}

	@Override
	public void deletePostLike(ActionDTO a) {
		// TODO Auto-generated method stub
		Action ac = actionConverter.toEntity(a);
		Action action =actionRepository.findOneByUser_IdAndPost_Id(ac.getUser().getId(),ac.getPost().getId()).get();
		System.out.println(action.getId());
		actionRepository.delete(action);
	}

}
