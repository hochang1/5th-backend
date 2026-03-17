package board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.dto.UserDto;
import board.dto.request.PostCommentRequest;
import board.dto.security.BoardDetails;
import board.entity.constant.UserRoleType;
import board.service.PostCommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
	
	private final PostCommentService postCommentService;
	
	
	@PostMapping
	public String registerNewPostComment(PostCommentRequest postCommentRequest, @AuthenticationPrincipal BoardDetails boardDetails) {
				
		postCommentService.registerPostComment(postCommentRequest.toDto(boardDetails.toDto()));
		
		return "redirect:/posts/" + postCommentRequest.getPid();
	}
	
	@PostMapping("/{pcid}/delete")
	public String deletePostComment(@PathVariable Long pcid, Long pid, @AuthenticationPrincipal BoardDetails boardDetails) {
		
		postCommentService.deletePostComment(pcid, pid, boardDetails.getUid());
		
		return "redirect:/posts/" + pid;
	}
}