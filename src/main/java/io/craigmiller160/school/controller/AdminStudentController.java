package io.craigmiller160.school.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.craigmiller160.school.entity.Gender;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.service.GenericEntityServiceBean;
import io.craigmiller160.school.util.DatePropertyEditor;

@Controller
@RequestMapping (value="/admin/student")
public class AdminStudentController {

	//TODO this and the course controller, how to handle HibernateExceptions???
	//TODO need data validation before saving an entity
	
	private final GenericEntityServiceBean service;
	
	@InitBinder (value="student")
	public void dateBinding(WebDataBinder binder){
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DatePropertyEditor editor = new DatePropertyEditor(formatter, true);
		binder.registerCustomEditor(LocalDate.class, editor);
	}
	
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
		model.addAttribute("morePages", service.hasPagesRemaining(
				Student.class, page, 10));
		
		return "students";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String createNewStudent(Student student, Model model){
		model.addAttribute("genders", Gender.values());
		return "student-form";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.PUT)
	public String insertStudent(Student student,
			@RequestParam (required=false) String cancel){
		//If the button selected wasn't cancel, it must be save
		if(cancel == null){
			service.insertEntity(student);
		}
		
		return "redirect:/admin/student/all.html?page=1";
	}
	
	//TODO might not need the model argument in this method
	@RequestMapping (value="/{studentId}", method=RequestMethod.GET)
	public String editStudent(Model model, Student student,
			@PathVariable ("studentId") String studentId){
		//TODO need a way to handle NumberFormatException here
		int id = Integer.parseInt(studentId);
		student = service.getEntityById(Student.class, id);
		model.addAttribute("student", student);
		model.addAttribute("genders", Gender.values());
		
		return "student-form";
	}
	
	//TODO might not need the model argument in this method
	@RequestMapping(value="/{studentId}", method=RequestMethod.POST)
	public String updateStudent(Student student, Model model,
			@PathVariable ("studentId") String studentId,
			@RequestParam (required=false) String cancel){
		//If the button selected wasn't cancel, it must be save
		if(cancel == null){
			service.updateEntity(student);
		}
		
		return "redirect:/admin/student/all.html?page=1";
	}
	
	@RequestMapping (value="/{studentId}", method=RequestMethod.DELETE)
	public String deleteStudent(@PathVariable ("studentId") String studentId){
		//TODO NumberFormatException needs to be handled
		int id = Integer.parseInt(studentId);
		service.deleteEntityById(Student.class, id);
		
		return "redirect:/admin/student/all.html?page=1";
	}
	
}
