package jpa.test;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Lecture;
import jpa.entity.Student;

public class MappingTest {
	public static void method(EntityManager em) {
		// insert
		// Tech 강좌 (Lecture가 부모테이블)
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);
//		
//		// 20242001, "jpa", Tech 강좌
//		Student stu = new Student();
//		stu.setLid(1L);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);
		
		// 위 내용create으로 해주고 끝나면 다시 none으로
		
		// select 
		// 학생이 참가하고 있는 수업의 정보 출력?	
		// 1) 학생 -> lid
		// 2) 수업 반환
//		Long lid = em.find(Student.class, 20242001).getLid();		//수업찾기
//		Lecture foundLec = em.find(Lecture.class, lid);
//		System.out.println(foundLec);
		
		// 해결 @JoinColumn + @ManyToOne -> create(foreign key 확인)
		// FK 반영 내용을 확인 후 -> none
		// insert 다시
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);		
//		
//		Student stu = new Student();
//		stu.setLecture(lec);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);
		
//		Lecture foundLecture = em.find(Student.class, 20242001).getLecture();
//		System.out.println(foundLecture);
		
		// 강좌에 참여하고 있는 학생의 정보 출력?
		// 객체간의 관계(방향성)-단방향 => 양방향 (mappedby 이용)
//		List<Student> students = em.find(Lecture.class, 1l).getStudents();
//		System.out.println(students);
		//오류발생 : mappedby로 주인 지칭, exclude 설정
		
		
		
		
		// SQL : SELECT * FROM student WHERE lid = ?; 		데이터바인딩
		
		// 이름 기반 파라미터 JPQL (사용권장:가독성좋음)
//		String namedJpql = "select s from Student s where s.lecture.lid = :lid";
//		List<Student> students = em.createQuery(namedJpql, Student.class)
//			.setParameter("lid", 1l)		//1이 의미하는게 멀까? 검색
//			.getResultList();
//		System.out.println(students);
		
		// 위치 기반 파라미터 JPQL
//		String positionalJpql = "select s from Student s where s.lecture.lid = ?1";
//		students = em.createQuery(positionalJpql, Student.class)
//					.setParameter(1,  1L)
//					.getResultList();
//		
//		System.out.println(students);
//		
	}
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	
		try {
			method(em);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
