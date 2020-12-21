package com.tmdt.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.tmdt.converter.PostConverter;
import com.tmdt.dto.FilterDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.entity.Evaluated;
import com.tmdt.entity.Image;
import com.tmdt.entity.Post;
import com.tmdt.entity.Star;
import com.tmdt.entity.User;
import com.tmdt.repository.EvaluatedRepository;
import com.tmdt.repository.PostRepository;
import com.tmdt.repository.PostSpecificaiton;
import com.tmdt.repository.StarRepository;
import com.tmdt.repository.UserRepository;
import com.tmdt.security.CustomUserDetail;
import com.tmdt.service.IPostService;

@Service
public class PostService implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostConverter postConverter;

	@Autowired
	private ImageService imageService;

	@Autowired
	private EvaluatedRepository evaluatedRepository;

	@Autowired
	private CustomUserDetail customUserDetail;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PostDTO findOneById(int id) {
		// TODO Auto-generated method stub
		PostDTO p = postConverter.toDTO(postRepository.findOneById(id).get());
		p.setTotalStar((double) Math.floor(totalStarEvaluated(id) * 10) / 10);
		return p;
	}

	@Override
	public boolean save(PostDTO post, MultipartFile[] files) {
		// TODO Auto-generated method stub
		User u = userRepository.findOneByUserName(customUserDetail.getPrinciple().getName()).get();
		if (u.getTotalMoney() - post.getFee().getPrice() < 0) {
			return false;
		}
		Post p = postConverter.toEntity(post);
		List<Image> l = new ArrayList<Image>();
		if (!files[0].getOriginalFilename().equals("")) {
			Arrays.asList(files).stream().forEach(file -> {

				String a;
				try {
					a = imageService.save(file);
					Image i = new Image();
					i.setName(a);
					i.setPost(p);
					l.add(i);
					System.out.println(i.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			p.setImages(l);
		}
		p.setBrowse(true);
		postRepository.save(p);
		return true;
	}

	private double totalStarEvaluated(int postId) {
		List<Evaluated> list = evaluatedRepository.findByPost_Id(postId);
		double init = 0.0;
		for (Evaluated e : list) {
			init += e.getStar().getValue();

		}
		return init / list.size();
	}

	@Override
	public List<PostDTO> findAllRef(int ward, int district, int provincial) {
		// TODO Auto-generated method stub
		return postRepository.findAllRef(ward, district, provincial).stream().map(e -> postConverter.toDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public Page<PostDTO> findAll(Map<String, String> q) {
		// TODO Auto-generated method stub
		int pageNumber = Integer.valueOf(q.get("pageNumber")) - 1;
		int pageSize = Integer.valueOf(q.get("pageSize"));
		int idWard = Integer.valueOf(q.get("ward") == null ? "0" : q.get("ward"));
		int idDistrict = Integer.valueOf(q.get("district") == null ? "0" : q.get("district"));
		int idProvincial = Integer.valueOf(q.get("provincial") == null ? "0" : q.get("provincial"));
		String areage = q.get("areage") == null ? "0" : q.get("areage");
		String price = q.get("price") == null ? "0" : q.get("price");
		String valueSort = q.get("sort") == null ? "0" : q.get("sort");

		Pageable pageable = null;
		if (!valueSort.equals("0") && valueSort != null) {
			Sort sort = null;
			if (valueSort.equals("areage")) {
				sort = Sort.by(Sort.Direction.ASC, "content.areage");
			} else {
				sort = Sort.by(Sort.Direction.ASC, "content.price");
			}
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		} else {
			pageable = PageRequest.of(pageNumber, pageSize);
		}
		FilterDTO f = new FilterDTO(idWard, idDistrict, idProvincial, areage, price);
		Specification<Post> spec = new PostSpecificaiton(f);
		return postRepository.findAll(spec, pageable).map(postConverter::toDTO);
	}

	@Override
	public List<PostDTO> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll().stream().map(postConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Page<PostDTO> findByUser_IdAndBrowse(Map<String, String> q) {
		int pageNumber = Integer.valueOf(q.get("pageNumber")) - 1;
		int pageSize = Integer.valueOf(q.get("pageSize"));

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		int idUser = Integer.valueOf(q.get("id") == null ? "0" : q.get("id"));
		return postRepository.findByUser_IdAndBrowse(pageable, idUser, true).map(postConverter::toDTO);
	}
}
