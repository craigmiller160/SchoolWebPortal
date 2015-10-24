package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping (value="/admin/course")
public class AdminCourseController {

	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getAllCourses(){
		System.out.println("Get all courses");
		return "courses";
	}
	
	
	
}
