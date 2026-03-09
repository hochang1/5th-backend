package com.spring.mvc.controller;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.dto.Student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MVCController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
	public void mvc() {
		System.out.println("mvc");
	}
	
	// http://localhost:8080/test11
	@GetMapping(value = "/test11")			
	public ResponseEntity<Student> test11() {				
		System.out.println("MVController : test11()");
		Student student = new Student(2, "cloud", "senior");
		
		// header, body 있어야 한다
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<Student>(student, headers, HttpStatus.OK);
	}
	

	
	// http://localhost:8080/test10
	@GetMapping(value = "/test10")			
	public String test10(Model model) {				
		System.out.println("MVController : test10()");
		Student student = new Student(2, "cloud", "senior");
		
		model.addAttribute("student", student);
		
		return "test";
	}
	
//	@GetMapping(value = "/test10")			
//	public ModelAndView test10(ModelAndView mv) {				
//		System.out.println("MVController : test10()");
//		Student student = new Student(2, "cloud", "senior");
//		
//		mv.addObject("student", student);
//		mv.setViewName("test");
//		
//		return mv;
//	}

	
	// http://localhost:8080/test9
	@GetMapping(value = "/test9")
	@ResponseBody			// JSON 타입의 데이터로 반환
	public Student test9() {				
		System.out.println("MVController : test9()");
		
		Student student = new Student(2, "cloud", "senior");
		
		return student;
	}
	
	
	// http://localhost:8080/test8		//pem -> property -> jsp파일생성
	@GetMapping(value = "/test8")
	public String test8() {				//type : String으로
		System.out.println("MVController : test8()");
		return "test";
	}

	
	// http://localhost:8080/test7?emp=7369
	// http://localhost:8080/test7/emp/7369

	@GetMapping(value = "/test7/emp/{empno}")	//{변수로 지정}
	public void test7(@PathVariable Integer empno) {
		System.out.println("MVCController : test7()");
		System.out.println("empno" + empno);
	}

	// http://localhost:8080/test6
	// {"sname":"web-mvc", "grade":"junior"}
	// 1) @RequestBody 생략불가능
	// 2) JSON -> java 객체로 매핑 (스프링부트 내부의 jackson 라이브러리가 자동으로 해줌)
	@PostMapping(value = "/test6")						//postmapping할때 바디내용을 넣어주려면 requestbody를 넣어줘야한다
	public String test6(@Valid @RequestBody Student student, 
						BindingResult bindingResult) {
		System.out.println("MVCController : test6()");
		System.out.println(student);
		if(bindingResult.hasErrors()) {
			return "student/form";
		}
		
		return "redirect:/students";
	}

	
	// http://localhost:8080/test5?sname=web-mvc&grade=junior
	@GetMapping(value = "/test5")
	public void test5(Student student) {
		System.out.println("MVCController : test5()");
		System.out.println(student);
	}
	
	// http://localhost:8080/test4?sid=web-mvc&grade=junior
	/*
	 * Student
	 * Integer sid
	 * String grade
	 * 
	 */
	@GetMapping(value = "/test4")
	public void test4(String sname,			//@RequestParam 생략된거임
					  String grade) {
		System.out.println("MVCController : test4()");
		Student student = new Student(1, sname, grade);
		System.out.println(student);
	}

	
	// http://localhost:8080/test3?sid=web-mvc
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public void test3(String sid) {
		System.out.println("MVCController : test3()");
		System.out.println("sid : " + sid);
	}

	
	// http://localhost:8080/test2?sid=web-mvc
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public void test2(HttpServletRequest request) {
		System.out.println("MVCController : test2()");
		
		String sid = request.getParameter("sid");
		System.out.println("sid : " + sid);
	}
	
	 //http://localhost:8080/test1
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1() {
		System.out.println("MVCController : test1()");
	}

	
}
