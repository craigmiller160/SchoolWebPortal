package io.craigmiller160.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.persist.GenericPaginatedJoinHolderService;

@Controller
@RequestMapping (value="/admin/course")
public class AdminCourseController {

	private final GenericPaginatedJoinHolderService service;
	
	@Autowired (required=true)
	public AdminCourseController(GenericPaginatedJoinHolderService service){
		this.service = service;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getAllCourses(Model model, @RequestParam int page){
		int firstRowVal = page * 10 - 10;
		List<Course> courses = service.getNextEntities(
				Course.class, firstRowVal, 10);
		model.addAttribute("courses", courses);
		model.addAttribute("page", page);
		
		return "courses";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String createNewCourse(Course course){
		return "course-form";
	}
	
	@RequestMapping (value="/new", method=RequestMethod.PUT)
	public String saveNewCourse(Course course){
		service.insertEntity(course);
		
		return "redirect:/admin/course/all.html?page=1";
	}
	
	@RequestMapping (value="/{courseId}", method=RequestMethod.GET)
	public String editCourse(Model model, Course course,
			@PathVariable ("courseId") String courseId){
		//TODO need a way to handle NumberFormatException here
		int id = Integer.parseInt(courseId);
		course = service.getEntityById(Course.class, id);
		model.addAttribute("course", course);
		
		return "course-form";
	}
	
	@RequestMapping (value="/{courseId}", method=RequestMethod.POST)
	public String saveEditCourse(Course course, Model model,
			@PathVariable ("courseId") String courseId,
			@RequestParam (required=false) String cancel){
		if(cancel == null){
			service.updateEntity(course);
		}
		
		return "redirect:/admin/course/all.html?page=1";
	}
	
	//TODO finish delete operation
	@RequestMapping (value="/{courseId}", method=RequestMethod.DELETE)
	public String deleteCourse(@PathVariable ("courseId") String courseId){
		System.out.println("Delete CourseID: " + courseId);
		
		return null;
	}
	
	
	
}
