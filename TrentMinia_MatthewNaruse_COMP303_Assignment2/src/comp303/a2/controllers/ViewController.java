/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import comp303.a2.entities.Customer;

@Controller
public class ViewController {
	private static HttpSession session;

	/* TEMPORARY CONTROLLER CLASS FOR HYPERLINKS */
	
	// Handled in ProductController
//	@GetMapping("/order")
//	public ModelAndView order() {
//		return new ModelAndView("order");
//	}
	

	@GetMapping("/index")
	public ModelAndView showCurrentUser(Model model, Customer cust, HttpServletRequest request) {
		return new ModelAndView("index.jsp");
	}
//	@GetMapping("/checkout")
//	public ModelAndView checkout() {
//		return new ModelAndView("checkout");
//	}

	
//	@GetMapping("/confirm-order")
//	public ModelAndView confirm_order() {
//		return new ModelAndView("confirm-order");
//	}
	
	// Handled in CustomerController
//	@GetMapping("/profile")
//	public ModelAndView profile() {
//		return new ModelAndView("profile");
//	}
	
	// Handled in CustomerController
//	@GetMapping("/register")
//	public ModelAndView register() {
//		return new ModelAndView("register");
//	}
	
//	@GetMapping("/view-order")
//	public ModelAndView view_order() {
//		return new ModelAndView("view-order");
//	}
}
