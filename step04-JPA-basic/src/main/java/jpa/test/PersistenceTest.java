package jpa.test;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Student;

public class PersistenceTest {
	public static void main(String[] args) {
		// EMF
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		
		
		// EM
		EntityManager em = emf.createEntityManager();
		
		// TX : begin() ~ 작업 수행 ~  commit()
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// insert
		// 20241001, "DEV"
		// 20241002, "DEVOPS"
//		Student stu1 = new Student(20241001, "DEV");
//		Student stu2 = new Student(20241002, "DEVOPS");
//		em.persist(stu1);
//		em.persist(stu2);
		
		
		// select
		// sid로 검색 20241001
//		Student foundStu1 = em.find(Student.class, 20241001);
//		System.out.println(foundStu1);
		
		// selectAll
		// JPQL
		// SELECT * FROM student -> SELECT s FROM Student s;			//쿼리를 가져오는게 아니라 엔터티 객체를 가져오는것이라 대문자로 시작하고 별칭을 반드시 적어줘야한다
//		List<Student> students =  em.createQuery("SELECT s FROM Student s", Student.class)
//								.getResultList();	
		
		// SELECT name FROM student -> SELECT s.name FROM Student s;		
//		List<String> names =  em.createQuery("SELECT s.name FROM Student s", String.class)
//								.getResultList();	
		
		
		
		// SELECT * FROM student;
//		students = em.createNativeQuery("SELECT * FROM student", Student.class)
//						.getResultList();
		
//		System.out.println(students);
		
		// update 				// 변경감지
		// DEVOPS 이름을 IT 변경
		// 1) find -> 1차 캐시에 스냅샷 저장
//		Student foundStu2 = em.find(Student.class, 20241002);		
//		System.out.println(foundStu2.getSname());			
//		
//		// 2) 값을 변경
//		foundStu2.setSname("IT");
//		System.out.println(foundStu2.getSname());
//		
//		// 3) commit 시점에서 스냅샷과 현재 객체 상태비교 -> 변경사항이 있다면 update
		
		
		
		
		
		// delete
		// 20241002 학생 삭제
//		Student foundStu2 = em.find(Student.class, 20241002);		
//		em.remove(foundStu2);
		
		
		
		
		// 특징
		// 1차캐쉬 (select 쿼리 한번만 사용됨, 이미 영속성 컨테스트에 저장되어있어서)
//		Student cashedStu1 = em.find(Student.class, 20241001);
//		System.out.println(cashedStu1);
//		
//		Student cashedStu2 = em.find(Student.class, 20241001);
//		System.out.println(cashedStu2);
		
		// 쓰기 지연
		// 20241003, "CLOUD"
		// 20241004, "JPA"
		// tx.commit()을 해야 실행됨
		Student stu3 = new Student(20241003, "CLOUD");
		Student stu4 = new Student(20241004, "JPA");
		em.persist(stu3);
		em.persist(stu4);
		
		// ****절대 실행햐지말것!!!! 문제 상황(1차캐시에 저장해야될 크기가 너무 클때) -> 해결방법
		// 1000건 단위로 메모리 초기화
		// 1) BATCH_SIZE = 1000;
		// 2) <property name="hibernate.jdbc.hatch_size" value="1000"/>
		/*
		for(int i = 0; i<100000; i++) {
			Student stu = new Student(i, "학생" + i);
			
			em.persist(stu);
			
			if(i %BATCH_SIZE ==0) {
				em.flush();      // 쓰기지연 저장소 -> DB 반영
				e.clear();		// 1차 캐시 초기화(메모리 확보)
				}
			}
		*/
		
		
		// 변경감지
		
		
		
	
		
		tx.commit();
	}
}
