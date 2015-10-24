package io.craigmiller160.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.persist.GenericPaginatedJoinHolderService;

@Controller
@RequestMapping (value="/admin/course")
public class AdminCourseController {

	private final GenericPaginatedJoinHolderService schoolService;
	
	@Autowired
	public AdminCourseController(GenericPaginatedJoinHolderService schoolService){
		this.schoolService = schoolService;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getAllCourses(Model model){
		List<Course> courses = schoolService.getNextEntities(
				Course.class, 0, 10);
		model.addAttribute("courses", courses);
		
		return "courses";
	}
	
	
	
}
