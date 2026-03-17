package board.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.PostDto;
import board.dto.UserDto;
import board.dto.request.SearchRequest;
import board.dto.response.PostResponse;
import board.dto.response.PostWithCommentsResponse;
import board.dto.security.BoardDetails;
import board.entity.constant.UserRoleType;
import board.service.PagingService;
import board.service.PostService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {
	
	private final PostService postService;
	private final PagingService pagingService;
	
	@GetMapping
	public String getPosts(@PageableDefault(size = 10) Pageable pageable, 		//application.properties에 index가 0이아니라 1부터 전달되도록 설정
							SearchRequest searchRequest,
							ModelMap map) {		
		
		
		// v5 page + search
		Page<PostResponse> posts = postService.getPostsWithSearchAndPage(searchRequest, pageable).map(PostResponse::from);

		List<Integer> pagingNumbers = pagingService.getPagingNumbers(pageable.getPageNumber() +1,
																	posts.getTotalPages());
			
		map.addAttribute("posts", posts);
		map.addAttribute("pagingNumbers", pagingNumbers);
		map.addAttribute("currentPage", pageable.getPageNumber() + 1);
		
		// v4 search
//		List<PostResponse> posts = postService.getPostsWithSearch(searchRequest).stream()
//												.map(PostResponse::from)		//dto를 PostResponse로 변경
//												.toList();
		
		// v3 : page 
//		Page<PostResponse> posts = postService.getPostsWithPage(pageable)
//				.map(PostResponse::from);

		
		//v2 :Postresponse 이용 
//		List<PostResponse> posts = postService.getPosts().stream()
//														.map(PostResponse::from)
//														.toList();
		
		// v1
	//	List<PostDto> posts = postService.getPosts();
		
		
		return "posts/index";
	}
	
	@GetMapping("/{pid}")
	public String getPost(@PathVariable Long pid,
											ModelMap map) {
		// v1 : post만 출력
//		PostDto post = postService.getPost(pid);
//		map.addAttribute("post", post);
		
		// v2 : post + comment 출력
		PostWithCommentsResponse post = PostWithCommentsResponse.from(postService.getPostWithComments(pid));
		map.addAttribute("post", post);
		map.addAttribute("comments", post.getPostCommentReponse());
		
		return "/posts/post-detail";
	}
	
	@GetMapping("/form")
	public String postFormPage() {
		return "/posts/post-form";
	}
	
	@GetMapping("/{pid}/form")
	public String updatePostFormPage(@PathVariable Long pid,
										ModelMap model) {
		PostDto post = postService.getPost(pid);
		model.addAttribute("post", post);
		
		return "/posts/post-form";
	}
	
	
	
	
	// 등록
	@PostMapping
	public String registerPost(
			PostDto postDto,
			@AuthenticationPrincipal BoardDetails boardDetails
			) {
		
		postService.registerPost(PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											boardDetails.toDto()));
		
		return "redirect:/posts";
	}
	
	//수정
	@PostMapping("/{pid}/edit")
	public String updatePost(@PathVariable Long pid, PostDto postDto, @AuthenticationPrincipal BoardDetails boardDetails) {

		postService.updatePost(pid, PostDto.of(postDto.getTitle(), 
											postDto.getContent(), 
											postDto.getCategoryType(), 
											boardDetails.toDto()));
		
		return "redirect:/posts/" + pid;
	}
	
	//삭제
	@PostMapping("/{pid}/delete")
	public String deletePost(@PathVariable Long pid, @AuthenticationPrincipal BoardDetails boardDetails ) {
		
		postService.deletePost(pid, boardDetails.getUid());
		
		return "redirect:/posts";
	}
	
	
}