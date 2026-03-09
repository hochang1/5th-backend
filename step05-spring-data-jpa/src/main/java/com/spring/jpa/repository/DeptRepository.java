package com.spring.jpa.repository;
//class 아니라 interface

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{

	Dept findByLoc(String loc);

	Dept findTop1ByLoc(String loc);

	List<Dept> findByDnameOrLoc(String dname, String loc);

	List<Dept> findByDeptnoBetween(int deptno1, int deptno2);

	List<Dept> findByDnameLike(String string);

	List<Dept> findByDnameContains(String string);

	List<Dept> findByDnameContainsOrderByDeptnoDesc(String string);

	List<Dept> findByDnameContains(String string, Sort by);

	Page<Dept> findByDnameContains(String string, PageRequest of);		//domain의 페이지

	List<Dept> findByDeptnoIn(List<Integer> deptnos);

	@Query("select d.dname from Dept d")	
	List<String> findAllDnames();			//직접 메서드 만들기
	
	@Query("select d.deptno from Dept d where d.loc = :loc")	
	List<Integer> findAllDeptnoByLoc(@Param("loc") String loc);			//직접 메서드 만들기
	

}
