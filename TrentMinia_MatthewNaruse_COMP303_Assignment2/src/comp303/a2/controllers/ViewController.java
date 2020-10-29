/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	/* TEMPORARY CONTROLLER CLASS FOR HYPERLINKS */
	
	@GetMapping("/order")
	public ModelAndView order() {
		return new ModelAndView("order");
	}
	
	@GetMapping("/checkout")
	public ModelAndView checkout() {
		return new ModelAndView("checkout");
	}
	
	@GetMapping("/confirm-order")
	public ModelAndView confirm_order() {
		return new ModelAndView("confirm-order");
	}
	
	@GetMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("profile");
	}
	
	// Handled in CustomerController
//	@GetMapping("/register")
//	public ModelAndView register() {
//		return new ModelAndView("register");
//	}
	
	@GetMapping("/view-order")
	public ModelAndView view_order() {
		return new ModelAndView("view-order");
	}
}
