package comp303.a2.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import comp303.a2.entities.Customer;

@Controller
public class CustomerController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView preplogin(Model model) {
		model.addAttribute("customer", new Customer());
		return new ModelAndView("login", "customer", new Customer());
	}
	
	@RequestMapping(value="/trylogin", method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("customer") Customer cust, 
								BindingResult result, 
								ModelMap model) {
		if (result.hasErrors()) return null;
		
		model.addAttribute("customer", cust);
		model.addAttribute("userName", cust.getUserName());
		model.addAttribute("password", cust.getPassword());
		
		return new ModelAndView("order");
	}
}
