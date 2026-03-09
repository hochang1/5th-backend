package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.view.dto.Contact;
import com.spring.view.dto.Student;
import com.spring.view.dto.Student.Grade;



@Controller
public class FormController {

	
	@GetMapping(value = "/form")
	public String form(Student student, Model model) {
		System.out.println("FormController");
		System.out.println(student);
		student.setGrade(Grade.JUNIOR);
		
		model.addAttribute("student", student);
		
		
		return "test";
	}

	@GetMapping(value = "/input-form")
	public String moveInputForm() {
		return "input";
	}
	
	@PostMapping(value = "/input-data")
	public String processInputData(Contact contact,
									Model model) {
		
		System.out.println(contact);
		model.addAttribute("contact", contact);
		
		return "output";
	}
	
	@GetMapping(value = "/update-form")
	public String moveUpdateForm(Model model) {
		Contact contact = new Contact("front",
									"shanna@mellssa.tv",
									"front");	
		
		model.addAttribute("contact", contact);
		return "update";
	}
	
	
	
}