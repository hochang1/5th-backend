package com.spring.jpa.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Dept;
import com.spring.jpa.repository.DeptRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeptService {
	
	private final DeptRepository deptRepository;
	
	@Transactional
	public Object jpaTest() {
		Object result = null;		// 이거 머냐 
		
		//save
		Dept newDept = new Dept(99, "JPA", "SEOUL");
//		result = deptRepository.save(newDept);			//save하고 주석처리
		
		// findById
//		result = deptRepository.findById(99)
//								.orElseThrow(() -> new NoSuchElementException());
		
		// findAll
//		result = deptRepository.findAll();
		
		// update
//		Dept foundDept = deptRepository.findById(99)
//									.orElseThrow(() -> new NoSuchElementException());
//		System.out.println(foundDept.getLoc());
//		foundDept.setLoc("BUSAN");
//		System.out.println(foundDept.getLoc());
		
		// delete
//		deptRepository.deleteById(99);		//delete는 void 반환
		
		
		
		// 쿼리메소드
		// find : loc 
//		result = deptRepository.findByLoc("BOSTON");		//정의해준다
		
		// find : loc
//		result = deptRepository.findTop1ByLoc("BOSTON");
		
		// find : dname or loc
//		result = deptRepository.findByDnameOrLoc("ACCOUNTING", "Boston");

		// find :deptno 10 between 30
//		result = deptRepository.findByDeptnoBetween(10, 30);
		
		// find : dname Like 'O' -> '%O'
//		result = deptRepository.findByDnameContains("O");
		
		// find : dname Like '%O%' -> Order By Deptno Desc
//		result = deptRepository.findByDnameContainsOrderByDeptnoDesc("O");
		
		// find : dname Like '%O%' -> Order By Deptno Desc
//		result = deptRepository.findByDnameContains("O", Sort.by(Order.desc("deptno")));

		// find : dname Like '%/a%' -> 1페이지당 2개씩 출력
//		result = deptRepository.findByDnameContains("A", PageRequest.of(0, 2));		
		// 참고 : LIMIT 2, 2 = LIMIT 2 OFFSET 2 : 앞에 두개(OFFSET 다음 숫자)를 건너뛰고 두개를 선택
		
		// find : deptno IN (20, 30)
//		List<Integer> deptnos =Arrays.asList(20, 30);
//		result = deptRepository.findByDeptnoIn(deptnos);
		
		// find : only dnames  -- 메소드 없음
//		result = deptRepository.findAllDnames();		//메소드가 없으니 직접 설정
		
		
		// 원본 손상가니 하지말것 ****
		//delete : deleteByLoc(String loc)
		//delete : 50번 이상 부서 삭제 : deleteByDeptnoGreaterThan(50)
		
		
		
		return result;
	}
	
	
}

// jpa에서의 crud방식
