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
import io.craigmiller160.school.service.GenericEntityServiceBean;

/**
 * The controller handling all interactions with
 * registered courses. It primary facilitates performing
 * CRUD operations with <tt>Course</tt> entities.
 * <p>
 * <b>THREAD SAFETY:</b> Because this controller will be used
 * in a servlet environment, it is designed to be completely 
 * thread safe.
 * 
 * @author craig
 * @version 1.0
 */
@Controller
@RequestMapping (value="/admin/course")
public class AdminCourseController {

	/**
	 * The class representing this program's service layer.
	 */
	private final GenericEntityServiceBean service;
	
	/**
	 * Create this controller and inject a reference to this
	 * program's service layer as well. If the service class
	 * is passed as null, this controller will fail in its 
	 * operations.
	 * 
	 * @param service the class representing this program's service 
	 * layer.
	 */
	@Autowired (required=true)
	public AdminCourseController(GenericEntityServiceBean service){
		this.service = service;
	}
	
	/**
	 * Retrieve a paginated list of all courses persisted in
	 * the database. The page retrieved is defined by the "page"
	 * HTTP request parameter.
	 * 
	 * @param model the model for the courses page to populate with 
	 * a list of courses.
	 * @param page the page number to retrieve courses for.
	 * @return the name of of the UI page to display
	 * the list of courses on. 
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getAllCourses(Model model, @RequestParam int page){
		//TODO allow for variable page sizes chosen by the user.
		List<Course> courses = service.getEntitiesByPage(
				Course.class, page, 10);
		model.addAttribute("courses", courses);
		model.addAttribute("page", page);
		
		return "courses";
	}
	
	/**
	 * Open a blank form for defining the attributes of
	 * a <tt>Course</tt> object in preparation for creating
	 * a new entity.
	 * 
	 * @param course the course to populate with values in
	 * the UI.
	 * @return the name of the UI page containing the form
	 * to display.
	 */
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String createNewCourse(Course course){
		return "course-form";
	}
	
	/**
	 * Insert a newly created <tt>Course</tt> entity into
	 * the database, using the service layer.
	 * 
	 * @param course the course to insert.
	 * @return a redirect to the first page of the courses
	 * list UI page.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if the persistence operation fails.
	 */
	@RequestMapping (value="/new", method=RequestMethod.PUT)
	public String insertCourse(Course course){
		service.insertEntity(course);
		
		return "redirect:/admin/course/all.html?page=1";
	}
	
	/**
	 * Open a form for defining the attributes of a
	 * <tt>Course</tt> entity with its fields already
	 * populated with the attributes of the selected
	 * course. Allows for those attributes to be edited
	 * by the user.
	 * 
	 * @param model the model defining values for the page. 
	 * @param course the course whose values will populate
	 * the fields of the form.
	 * @param courseId the ID of the course whose values will
	 * populate the fields of the form.
	 * @return the name of the UI page containing the form
	 * to display.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if the persistence operation fails.
	 */
	//TODO might not need the model argument in this method
	@RequestMapping (value="/{courseId}", method=RequestMethod.GET)
	public String editCourse(Model model, Course course,
			@PathVariable ("courseId") String courseId){
		//TODO need a way to handle NumberFormatException here
		int id = Integer.parseInt(courseId);
		course = service.getEntityById(Course.class, id);
		model.addAttribute("course", course);
		
		return "course-form";
	}
	
	/**
	 * Update the values of an edited course in the database.
	 * Only performs the operation if the "cancel" HTTP request
	 * parameter is null.
	 * 
	 * @param course the course whose values are to be updated.
	 * @param model the model defining values for the page.
	 * @param courseId the ID of the course whose values are 
	 * to be updated.
	 * @param cancel an HTTP request parameter that needs to
	 * be null for the update operation to be performed.
	 * @return a redirect to the first page of the courses
	 * list UI page.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if the persistence operation fails.
	 */
	//TODO might not need the model argument in this method
	@RequestMapping (value="/{courseId}", method=RequestMethod.POST)
	public String updateCourse(Course course, Model model,
			@PathVariable ("courseId") String courseId,
			@RequestParam (required=false) String cancel){
		//If the button selected wasn't cancel, it must be save
		if(cancel == null){
			service.updateEntity(course);
		}
		
		return "redirect:/admin/course/all.html?page=1";
	}
	
	/**
	 * Delete a specified course from the database.
	 * 
	 * @param courseId the ID of the course to be deleted.
	 * @return a redirect to the first page of the courses
	 * list UI page.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if the persistence operation fails.
	 */
	@RequestMapping (value="/{courseId}", method=RequestMethod.DELETE)
	public String deleteCourse(@PathVariable ("courseId") String courseId){
		//TODO NumberFormatException needs to be handled
		int id = Integer.parseInt(courseId);
		service.deleteEntityById(Course.class, id);
		
		return "redirect:/admin/course/all.html?page=1";
	}
	
	
	
}
