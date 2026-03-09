package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.jpa.entitiy.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

	@Query("select l from Lecture l join fetch l.students")
	List<Lecture> findAllWithFetchJoin();

	@Query("select l from Lecture l left join fetch l.students")
	List<Lecture> findAllWithOuterFetchJoin();

	@EntityGraph(attributePaths = {"students"})
	@Query("select l from Lecture l")
	List<Lecture> findAllWithEntityGraph();

}
