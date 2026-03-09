package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entitiy.Lecture;
import com.spring.jpa.entitiy.Student;
import com.spring.jpa.repository.LectureRepository;
import com.spring.jpa.repository.StudentRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApplyService {
	
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	
	@Transactional
	public Object jpaApply() {
		Object result = null;
		// 비즈니스 로직 : 강의 수강하는 학생들의 이름 출력하려면?
		/*
		 [
		    "HTML",
		    "CSS",
		    "JAVASCRIPT",
		    "SERVER",
		    "SERVLET",
		    "JSP",
		    "DI",
		    "MVC",
		    "JPA"
		]
		 */

		// N + 1 해결	 : 
		
		
		//@OneToMany(mappedBy = "lecture", fetch = FetchType.EAGER) : fetchType을 lazy -> eager로 바꿔주면 2개의 쿼리가 하나로 join
//		result = lectureRepository.findById(1L);	
		
		// 여려개 가져올땐 n+1 해결 안됨  --lec 1000개 -> (student1000개+lec1개) 10001개 쿼리 
//		result = lectureRepository.findAll();		
		
		// 해결방법 
		// 1) 패치 조인(이너조인)  --학생이 null인 강좌는 안나옴
//		result = lectureRepository.findAllWithFetchJoin();
		// null을 해결하기위해 outer조인
//		result = lectureRepository.findAllWithOuterFetchJoin();
		
		// 2) 엔터티 그래프
//		result = lectureRepository.findAllWithEntityGraph();
		
		// 단점 : 1), 2) 페이지 처리가 불가능
		
		//3) @BatchSize, fetch = FetchType.EAGER
//		result = lectureRepository.findAll();
		
		/*
		 * 페이징 필요?
		 *		yes -> @BatchSize
		 *		no -> 데이터 규모?
		 *					소규모, 단순 -> fetch join
		 *					대규모 	  -> @BatchSize
		 *					동적 처리	  -> EntityGraph
		 * 
		 * 실무에선 기본적으로 @BatchSize 
		 * 
		 * N + 1 해결 주요 목적 : 쿼리를 최소화
		 * 
		 * 만약 강좌 25개, batchsize = 10
		 * 발생하는 쿼리는?
		 * 1: select * from lecture
		 * 2: where lecture_id In (1~10)
		 * 3: where lecture_id In (11~20)
		 * 4: where lecture_id In (21~25)
		 */
		
		
		
		return result;
	}
	
	@Transactional
	public List<Lecture> osivApply() {
		return lectureRepository.findAll();
	}
	
	@Transactional
	public Object cascadeApply() {
		
//		Lecture lecture = new Lecture();
//		lecture.setLname("API");
//
//		Student stu1 = new Student();
//		stu1.setSid(20244001);
//		stu1.setSname("REST");
//		stu1.setLecture(lecture);	
//	
//		Student stu2 = new Student();
//		stu2.setSid(20244002);
//		stu2.setSname("API");
//		stu2.setLecture(lecture);
//		
//		lecture.getStudents().add(stu1);
//		lecture.getStudents().add(stu2);
		
		// lecture와 연결이 끊김 --코드로만 보자(실행 조심)
		// orphanRemoval = true : 고아삭제
//		lecture.getStudents().remove(0);		// stu1 고아됨
		
		// cascade로 stu1, stu2도 함께 저장(영속성 전이)
		// cascade = CascadeType.PERSIST -- 이거 없으면 lecture만 insert
//		lectureRepository.save(lecture);	
		
		return null;
	}
	
}