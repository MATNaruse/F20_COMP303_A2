package comp303.a2.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import comp303.a2.entities.Customer;

@Controller
public class CustomerController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showlogin() {
		return new ModelAndView("index", "customer", new Customer());
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("customer")Customer cust, 
								BindingResult result, 
								ModelMap model) {
		if (result.hasErrors()) return new ModelAndView("index");
		
		model.addAttribute("username", cust.getUserName());
		model.addAttribute("password", cust.getPassword());
		
		return new ModelAndView("order");
	}
}
