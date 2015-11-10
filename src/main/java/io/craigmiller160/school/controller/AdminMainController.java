package io.craigmiller160.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
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
public class AdminMainController {

	/**
	 * Display the main menu page for the admin
	 * portal.
	 * 
	 * @return the name of the UI page for the admin
	 * main menu.
	 */
	@RequestMapping (method=RequestMethod.GET)
	public String showMainPage(){
		return "main-menu";
	}
	
	/**
	 * Logout of the admin portal.
	 * 
	 * @return a redirect to the welcome page.
	 */
	@RequestMapping (value="/logout", method=RequestMethod.GET)
	public String logout(){
		//TODO this will need authentication logic to complete 
		//the logout process.
		return "redirect:/welcome.html";
	}
	
}
