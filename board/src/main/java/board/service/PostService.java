package board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import board.dto.PostDto;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	
    public PostDto getPost(Long pid) {
    	return postRepository.findById(pid)
    							.map(PostDto::from)
    							.orElseThrow(() -> new NoSuchElementException("해당 게시글 존재 x"));
    }
    
	@Transactional
    public void registerPost(PostDto postDto) {
		//dto -> entity로 바꿔서 저장
		// 로그인 가정 : save 처음 1번만하고 주석처리
		// 2. --> index파일에서 바뀐내용을 어떻게 적용할지 티임리프함수 변경
//    	User user = userRepository.save(User.of(postDto.getUserDto().getUid(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getPassword(),
//    											postDto.getUserDto().getEmail(),
//    											postDto.getUserDto().getUserRoleType()));
    	User user = userRepository.getReferenceById(postDto.getUserDto().getUid());
		
    	Post post = postDto.toEntity(user);
    	postRepository.save(post);
    }
	
    public void updatePost(Long pid, PostDto postDto) {
    	
    }
	
    public void deletePost(long pid, String uid) {

    }
    //3. 엔터티를 dto로 바꿔서 리턴
	public List<PostDto> getPosts() {
		return postRepository.findAll().stream()
										.map(PostDto::from)
	//									.map(post -> PostDto.from(post))
										.toList();
	}
	
}