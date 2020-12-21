package com.tmdt.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmdt.dto.PostDTO;
import com.tmdt.service.IEvaluatedService;
import com.tmdt.service.IFeedbackService;
import com.tmdt.service.IPostService;
import com.tmdt.service.IUserService;
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
	private EmailService emailService;

	@Autowired
	private IPostService postService;

	@PostMapping("/posts/feedback")
	public String onFeedBack(@RequestBody FeedbackRequest p) {
		evaluatedService.save(p.getStar(), p.getIdPost());
		feedBackService.save(p.getComment(), p.getIdPost());
		return "ok";
	}

	@PostMapping("/api/verify")
	public String onFeedBack(@RequestParam int id) {
		userService.reSendMail(id);
		return "ok";
	}

	@GetMapping("/list_post")
	public Page<PostDTO> list(@RequestParam(required=false) Map<String,String> q) {
		 return postService.findAll(q);
	}
	
	@GetMapping("/posts/created")
	public Page<PostDTO> postCreated(@RequestParam(required=false) Map<String,String> q){
		System.out.println(q);
		return postService.findByUser_IdAndBrowse(q);
	}
}
