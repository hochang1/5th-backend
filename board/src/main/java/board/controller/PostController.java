package board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostDto;
import board.dto.UserDto;
import board.entity.Post;
import board.entity.constant.UserRoleType;
import board.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {
	
	private final PostService postService;
	
	@GetMapping
	public String getPosts(ModelMap map) {
		
		List<PostDto> posts = postService.getPosts();
		map.addAttribute("posts", posts);
		
		return "posts/index";
	}
	
	@GetMapping("/{pid}")
	public String getPost(@PathVariable Long pid,
											ModelMap map) {
		PostDto post = postService.getPost(pid);
		map.addAttribute("post", post);
		
		return "/posts/post-detail";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "/posts/post-form";
	}
	
	@PostMapping
	public String registerPost(
			PostDto postDto
	) {
		//1.잘 넘겨져오는지 확인
//		System.out.println("----");
//		System.out.println(postDto);
		
		// 로그인됐다고 가정하는 코드 아래한줄
		UserDto userDto = UserDto.of("admin", "admin", "admin@board.com", "admin", UserRoleType.ROLE_ADMIN);
		postService.registerPost(PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											userDto));
		
		return "redirect:/posts";
	}
	
}