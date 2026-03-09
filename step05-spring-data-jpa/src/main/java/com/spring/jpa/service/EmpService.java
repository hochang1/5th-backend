package com.spring.jpa.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpService {

	private final EmpRepository empRepository;
	
	public Object empTest()	{
		Object result = null;
		
		// 저장
//		Emp newEmp = Emp.builder()
//		        .empno(9999)
//		        .ename("CLOUD")
//		        .job("MANAGER")
//		        .mgr(7839)
//		        .hiredate(LocalDate.of(2026, 3, 3))
//		        .sal(3000.0)
//		        .comm(500.0)
//		        .dept(new Dept(10, "ACCOUNTING", "NEW YORK"))
//		        .build();
//				
//		result = empRepository.save(newEmp);
		
		// ID(9999)로 조회
//		result = empRepository.findById(9999)
//								.orElseThrow(() -> new NoSuchElementException());
		
		// ID(9999)로 삭제
//		empRepository.deleteById(9999);
		
		// **job or dept로 조회("MAVAGER", 20)
//		result = empRepository.findByJobOrDept_Deptno("MAVAGER", 20);
		
		// 1000.0 < sal < 3000.0
//		result = empRepository.findBySalBetween(1000.0, 3000.0);

		// 이름 "A"포함 empno desc정렬
//		result = empRepository.findByEnameContainsOrderByEmpnoDesc("A");
		
		// 이름 "A"포함 empno desc정렬
//		result = empRepository.findByEnameContains("A", Sort.by(Order.desc("empno")));

		// 페이징 처리(0, 3)
//		result = empRepository.findByEnameContains("A", PageRequest.of(0, 3));
	
		// 특정 부서 (10, 20, 30) 사원 목록(in절)
//		List<Integer> deptnos = Arrays.asList(10, 20, 30);
//		result = empRepository.findByDept_DeptnoIn(deptnos);
		
		// 부서번호로 사원 이름 목록 조회
//		result = empRepository.findEnamesByDept_Deptno(10);
		
		return result;
	}
}
