package io.craigmiller160.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.service.GenericEntityServiceBean;

@Controller
@RequestMapping (value="/admin/student")
public class AdminStudentController {

	private final GenericEntityServiceBean service;
	
	@Autowired (required=true)
	public AdminStudentController(GenericEntityServiceBean service){
		this.service = service;
	}
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public String getAllStudents(Model model, @RequestParam int page){
		//TODO allow for variable page sizes chosen by the user.
		List<Student> students = service.getEntitiesByPage(
				Student.class, page, 10);
		model.addAttribute("students", students);
		model.addAttribute("page", page);
		
		return "students";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String createNewStudent(Student student){
		return "student-form";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.PUT)
	public String insertStudent(Student student){
		System.out.println("Inserting!!!");
		
		return "redirect:/admin/student/all.html?page=1";
	}
	
}
