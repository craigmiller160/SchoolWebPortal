package io.craigmiller160.school.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.craigmiller160.school.entity.SchoolUser;

/**
 * TODO update this documentation for the new name/purpose.
 * 
 * Controller for the main menu and other primary 
 * operations of the admin web portal. Currently
 * just displays the main menu page and handles
 * logout operations.
 * <p>
 * <b>THREAD SAFETY:</b> Because this controller will be used
 * in a servlet environment, it is designed to be completely 
 * thread safe.
 * 
 * @author craig
 * @version 1.0
 */
@Controller
@RequestMapping (value="/portal")
public class MainMenuController {

	/*@ModelAttribute (value="userName")
	public String userName(){
		SecurityContext secContext = SecurityContextHolder.getContext();
		Object principal = secContext.getAuthentication().getPrincipal();
		if(principal instanceof SchoolUser){
			return ((SchoolUser) principal).getUsername();
		}
		else{
			//TODO think about the else condition here
			return "UserName";
		}
	}*/
	
	@ModelAttribute
	public void setAttributes(Model model){
		SecurityContext secContext = SecurityContextHolder.getContext();
		Object principal = secContext.getAuthentication().getPrincipal();
		if(principal instanceof SchoolUser){
			SchoolUser user = (SchoolUser) principal;
			model.addAttribute("userName", user.getUsername());
		}
		//TODO think about an else condition here
	}
	
	
	
	/**
	 * Display the main menu page for the web
	 * portal.
	 * 
	 * @return the name of the UI page for the
	 * main menu.
	 */
	@RequestMapping (method=RequestMethod.GET)
	public String showMainPage(Model model){
		model.addAttribute("pageName", "mainMenu");
		
		/*SecurityContext secContext = SecurityContextHolder.getContext();
		//TODO need master level settings too, that'll wait till the end
		Object principal = secContext.getAuthentication().getPrincipal();
		if(principal instanceof SchoolUser){
			//TODO need an else condition for this
			SchoolUser user = (SchoolUser) principal;
			if(user.getAuthorities().contains(new UserRole(Role.ROLE_ADMIN))){
				model.addAttribute("pageName", "adminMainMenu");
			}
			else{
				model.addAttribute("pageName", "studentMainMenu");
			}
		}*/
		
		return "main-menu";
	}
	
}
