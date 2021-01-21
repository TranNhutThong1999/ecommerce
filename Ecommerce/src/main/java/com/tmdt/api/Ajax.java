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
import com.tmdt.dto.FeedBackDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.dto.UserDTO;
import com.tmdt.entity.StatePost;
import com.tmdt.service.IFeedbackService;
import com.tmdt.service.IImageService;
import com.tmdt.service.IPostService;
import com.tmdt.service.IUserService;
import com.tmdt.service.imp.ActionService;

@RestController
public class Ajax {

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
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String onFeedBack(@RequestBody FeedbackRequest p) {
		if (feedBackService.save(p.getComment(), p.getIdPost(),p.getStar())) {
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
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public Page<PostDTO> postCreated(@RequestParam(required = false) Map<String, String> q) {
		System.out.println(postService.findByUser_Id(q).getSize());
		return postService.findByUser_Id(q);
	}

	@GetMapping("/posts/like")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public Page<PostDTO> likePost(@RequestParam(required = false) Map<String, String> q) {

		return actionService.findAllByUser_IdAndPost_State(q);
	}

	@PutMapping("/like")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String likePost(@RequestParam int idPost) {
		ActionDTO a = new ActionDTO();
		a.setName("like");
	
		a.setIdPost(idPost);
		actionService.save(a);
		System.out.println("like");
		return "ok";
	}

	@DeleteMapping("/delete_post_created")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String deletPostCreated(@RequestParam int idPost) {
		postService.deletePostCreated(idPost);
		return "ok";
	}

	@DeleteMapping("/delete_post_like")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String deletPostLike(@RequestParam int idPost) {
		ActionDTO a = new ActionDTO();
		a.setIdPost(idPost);
		actionService.deletePostLike(a);
		return "ok";
	}

	@DeleteMapping("/delete_image")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') ")
	public String deleteImage(@RequestParam int idImage) {
		System.out.println(idImage);
		imageService.delete(idImage);
		return "ok";
	}

	@DeleteMapping("/admin/delete_user/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String deleteUser(@PathVariable int id) {
		userService.delete(id);
		System.out.println("delete " + id);
		return "ok";
	}

	@GetMapping("/admin/list_user")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public Page<UserDTO> listUsers(@RequestParam(required = false) Map<String, String> q) {
		Page<UserDTO> s =userService.findAll(q);
		return s;
	}

	@GetMapping("/admin/list_Post_NotApprove")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public Page<PostDTO> listPostNotApprove(@RequestParam(required = false) Map<String, String> q) {
		return postService.findByState(q, StatePost.NotApproved);
	}

	@DeleteMapping("/admin/delete_post/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String deletePost(@PathVariable int id) {
		postService.deletePostCreated(id);
		return "ok";
	}

	@PutMapping("/admin/approve_post/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String approvePost(@PathVariable int id) {
		postService.apporve(id);
		return "ok";
	}
	@GetMapping("/admin/list_Post")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public Page<PostDTO> listPostApprove(@RequestParam(required = false) Map<String, String> q) {
		return postService.findByState(q, StatePost.Approved);
	}
	
	@GetMapping("/admin/list_FeedBack")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public Page<FeedBackDTO> listFeedBack(@RequestParam(required = false) Map<String, String> q) {
		 Page<FeedBackDTO> s = feedBackService.findAll(q);
		 return s;
	}
	
	@DeleteMapping("/admin/delete_feedback/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN') ")
	public String deleteFeedback(@PathVariable int id) {
		feedBackService.delete(id);
		return "ok";
	}
}
