package board.service;

import org.springframework.stereotype.Service;

import board.dto.PostCommentDto;
import board.entity.Post;
import board.entity.PostComment;
import board.entity.User;
import board.repository.PostCommentRepository;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostCommentService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostCommentRepository postCommentRepository;
	
	@Transactional
	public void registerPostComment(PostCommentDto postCommentDto) {
		
		// User 누구인가
		User user = userRepository.getReferenceById(postCommentDto.getUserDto().getUid());
		
		// Post 어떤 post인가
		Post post = postRepository.getReferenceById(postCommentDto.getPid());
		
		// PostCommentDto -> PostComment(dto -> entity)
		PostComment postComment = postCommentDto.toEntity(post, user);
		
		postCommentRepository.save(postComment);
	}
	
	@Transactional
	public void deletePostComment(Long pcid, Long pid, String uid) {
//		postCommentRepository.deleteByIdAndUser_Uid(pcid, uid);
		postCommentRepository.deleteByIdAndPost_IdAndUser_Uid(pcid, pid, uid);
	}
	
}