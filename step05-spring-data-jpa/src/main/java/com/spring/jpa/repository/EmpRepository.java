package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.jpa.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

	List<Emp> findByJobOrDept_Deptno(String job, Integer deptno);

	List<Emp> findBySalBetween(double sal1, double sal2);

	List<Emp> findByEnameContainsOrderByEmpnoDesc(String ename);

	List<Emp> findByEnameContains(String ename, Sort by);

	Page<Emp> findByEnameContains(String ename, PageRequest of);

	List<Emp> findByDept_DeptnoIn(List<Integer> deptnos);

	@Query("select e.ename from Emp e where e.dept.deptno =:deptno")
	List<String> findEnamesByDept_Deptno(@Param("deptno") Integer deptno);


	

	


}
