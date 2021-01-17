package com.tmdt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tmdt.dto.EvaluatedDTO;
import com.tmdt.dto.PostDTO;
import com.tmdt.service.IDistrictService;
import com.tmdt.service.IFeeService;
import com.tmdt.service.IProvincialService;
import com.tmdt.service.IStarService;
import com.tmdt.service.IUserService;
import com.tmdt.service.IWardService;
import com.tmdt.service.imp.PostService;
import com.tmdt.service.imp.StarService;

@Controller
public class PostController {
	@Autowired
	private IWardService wardService;

	@Autowired
	private IDistrictService districtService;

	@Autowired
	private IProvincialService provincialService;

	@Autowired
	private IFeeService feeService;

	@Autowired
	private PostService postService;

	@Autowired
	private IStarService starService;
	
	@Autowired
	private IUserService userService;
	
	private void mapData(ModelMap modelMap) {
		modelMap.addAttribute("Wards", wardService.findAll());
		modelMap.addAttribute("Districts", districtService.findAll());
		modelMap.addAttribute("Provincials", provincialService.findAll());
		modelMap.addAttribute("Fees", feeService.findAll());
	}
	@GetMapping("/posts")
	public String displayPosts(ModelMap modelMap) {
		mapData(modelMap);
		return "Posts";
	}
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') and @validate.checkIdPostUser(#id, principal.id)")
	public String displayPostEdit(ModelMap modelMap, @PathVariable int id) {
		PostDTO p =postService.findOneById(id);
		modelMap.addAttribute("Post", p);
		mapData(modelMap);
		return "/NewPost";
	}
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') and @validate.checkPostUser(#post, principal.id)")
	public String submitUpdate(@Valid @ModelAttribute("Post") PostDTO post, BindingResult error, ModelMap modelMap,
			@RequestParam(value = "files", required = false) MultipartFile[] files) {
		mapData(modelMap);
		if (error.hasErrors()) {
			return "NewPost";
		}
		PostDTO postDTO = postService.update(post, files);
		if(postDTO == null) {
			modelMap.addAttribute("message", "updatePost_fail");
			return "NewPost";
		}
		return "redirect:/posts/"+postDTO.getId()+"?message=update_success";
	}
	
	@GetMapping("/new")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public String displayNewPost(ModelMap modelMap) {
		modelMap.addAttribute("Post", new PostDTO());
		mapData(modelMap);
		return "NewPost";
	}

	@PostMapping("/new")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN') and @validate.checkPostUser(#post,principal.id)")
	public String submitNewPost(@Valid @ModelAttribute("Post") PostDTO post, BindingResult error, ModelMap modelMap,
			@RequestParam(value = "files", required = false) MultipartFile[] files) {
		mapData(modelMap);
		if (error.hasErrors()) {
			return "NewPost";
		}
		PostDTO postDTO = postService.save(post, files);
		if(postDTO == null) {
			System.out.println("max money");
			modelMap.addAttribute("message", "newPost_fail");
			return "NewPost";
		}
		return "redirect:/posts/"+postDTO.getId()+"?message=new_success";
	}

	@GetMapping("/posts/{id}")
	public String postDetail(@PathVariable int id, ModelMap modelMap, @RequestParam(required = false, defaultValue = "") String message) {
		if(message.equals("new_success")) {
			modelMap.addAttribute("message", "newPost");
		}
		if(message.equals("update_success")) {
			modelMap.addAttribute("message", "updatePost");
		}
		PostDTO p = postService.findOneById(id);
		postService.saveView(p.getId());
		double totalStar = p.getEvaluated().size();
		modelMap.addAttribute("Post", p);
		modelMap.addAttribute("User", userService.findOneById(p.getUserId()));
		modelMap.addAttribute("Stars", starService.findAll());
		modelMap.addAttribute("fiveStar", countStarInOnePost(5, p.getEvaluated())/totalStar*100);
		modelMap.addAttribute("fourStar", countStarInOnePost(4, p.getEvaluated())/totalStar*100);
		modelMap.addAttribute("threeStar", countStarInOnePost(3, p.getEvaluated())/totalStar*100);
		modelMap.addAttribute("twoStar", countStarInOnePost(2, p.getEvaluated())/totalStar*100);
		modelMap.addAttribute("oneStar", countStarInOnePost(1, p.getEvaluated())/totalStar*100);
		List<PostDTO> l= postService.findAllRef(p.getAddress().getWard().getId(), p.getAddress().getDistrict().getId(), p.getAddress().getProvincial().getId());
		modelMap.addAttribute("PostRefs", l);
		return "PostDetail";
	}

	private double countStarInOnePost(int star, List<EvaluatedDTO> l) {
		double result = 0.0;
		for (EvaluatedDTO e : l) {
			result += e.getStar().getValue() == star ? 1 : 0;
		}
		return result;
	}
}
