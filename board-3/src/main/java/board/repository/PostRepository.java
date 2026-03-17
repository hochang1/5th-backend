package board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import board.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	void deleteByIdAndUser_Uid(long pid, String uid);

	//검색만
	List<Post> findByTitleContains(String searchValue);
	List<Post> findByContentContains(String searchValue);
	List<Post> findByUser_UidContains(String searchValue);

	//페이지+검색
	Page<Post> findByTitleContains(String searchValue, Pageable pageable);
	Page<Post> findByContentContains(String searchValue, Pageable pageable);
	Page<Post> findByUser_UidContains(String searchValue, Pageable pageable);

	

}