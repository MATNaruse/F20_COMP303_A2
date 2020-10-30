/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
	private static EntityManagerFactory factory;
	private static EntityManager eMngr;
	
	// Login Page - GET
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView preplogin(Model model) {
		return new ModelAndView("login", "customer", new Customer());
	}
	
	// Login Page - POST
	@RequestMapping(value="/trylogin", method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("customer") Customer cust, 
								BindingResult result, 
								ModelMap model) {
		if (result.hasErrors()) return null;
		
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
		
		eMngr.getTransaction().begin();
		Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
		Customer loginCustomer = (Customer) q_getByUsername.getSingleResult();
		Boolean passMatch = cust.getPassword().equals(loginCustomer.getPassword());
		eMngr.close();
		
		
		if(passMatch){
			return new ModelAndView("profile", "cust", loginCustomer);
		}
		
		else {
			return new ModelAndView("login", "cust", cust);
		}
	}
	
	
	// Register Page - GET
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView freshRegister(Model model) {
		return new ModelAndView("register", "customer", new Customer());
	}
	
	
	// Register Page - POST
	@RequestMapping(value="newregister", method=RequestMethod.POST)
	public ModelAndView registerNew(@Valid @ModelAttribute("customer") Customer cust, 
									BindingResult result, 
									ModelMap model) {
		if (result.hasErrors()) return null;
		model.addAttribute("customer", cust);
		
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
		
		eMngr.getTransaction().begin();
		eMngr.persist(cust);
		eMngr.getTransaction().commit();
		eMngr.close();

		return new ModelAndView("profile", "cust", cust);
	}
}
