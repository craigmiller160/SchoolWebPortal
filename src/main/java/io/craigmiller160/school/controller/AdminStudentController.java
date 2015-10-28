package io.craigmiller160.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.craigmiller160.school.service.GenericEntityServiceBean;

@Controller
@RequestMapping (value="/admin/student")
public class AdminStudentController {

	private final GenericEntityServiceBean schoolService;
	
	@Autowired (required=true)
	public AdminStudentController(GenericEntityServiceBean schoolService){
		this.schoolService = schoolService;
	}
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public String getAllStudents(Model model){
		
		
		return "students";
	}
	
}
