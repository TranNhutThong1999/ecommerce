 package com.tmdt.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmdt.dto.ActionDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.entity.Action;
import com.tmdt.service.IEvaluatedService;
import com.tmdt.service.IFeedbackService;
import com.tmdt.service.IImageService;
import com.tmdt.service.IPostService;
import com.tmdt.service.IUserService;
import com.tmdt.service.imp.ActionService;
import com.tmdt.util.EmailService;

@RestController
public class Ajax {
	@Autowired
	private IEvaluatedService evaluatedService;

	@Autowired
	private IFeedbackService feedBackService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IPostService postService;

	@Autowired
	private ActionService actionService;

	@Autowired
	private IImageService imageService;
	@PostMapping("/posts/feedback")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String onFeedBack(@RequestBody FeedbackRequest p) {
		if(evaluatedService.save(p.getStar(), p.getIdPost())) {
			feedBackService.save(p.getComment(), p.getIdPost());
			return "ok";
		}
		return "exist";
	}

	@PostMapping("/api/verify")
	public String onFeedBack(@RequestParam int id) {
		userService.reSendMail(id);
		return "ok";
	}

	@GetMapping("/list_post")
	public Page<PostDTO> list(@RequestParam(required = false) Map<String, String> q) {
		return postService.findAll(q);
	}

	@GetMapping("/posts/created")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public Page<PostDTO> postCreated(@RequestParam(required = false) Map<String, String> q) {
		System.out.println(postService.findByUser_Id(q).getSize());
		return postService.findByUser_Id(q);
	}

	@GetMapping("/posts/like")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public Page<PostDTO> likePost(@RequestParam(required = false) Map<String, String> q) {
		
		return actionService.findAllByUser_IdAndPost_State(q).map(e -> e.getPost());
	}

	@PutMapping("/like")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String likePost(@RequestParam int idPost) {
		ActionDTO a = new ActionDTO();
		a.setName("like");
		PostDTO p = new PostDTO();
		p.setId(idPost);
		
		a.setPost(p);
		actionService.save(a);
		System.out.println("like");
		return "ok";
	}
	@DeleteMapping("/delete_post_created")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String deletPostCreated(@RequestParam int idPost) {
		postService.deletePostCreated(idPost);
		return "ok";
	}
	@DeleteMapping("/delete_post_like")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public String deletPostLike(@RequestParam int idPost) {
		ActionDTO a = new ActionDTO();
		PostDTO p = new PostDTO();
		p.setId(idPost);
		a.setPost(p);
		actionService.deletePostLike(a);
		return "ok";
	}
	@DeleteMapping("/delete_image")
	public String deleteImage(@RequestParam int idImage) {
		System.out.println(idImage);
		imageService.delete(idImage);
		return "ok";
	}
}
