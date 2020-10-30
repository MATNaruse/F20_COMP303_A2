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
		try {
			// Find the Customer from Table using Username
			eMngr.getTransaction().begin();
			Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
			Customer loginCustomer = (Customer) q_getByUsername.getSingleResult();
			eMngr.close();
			
			// If Passwords Match
			if(cust.getPassword().equals(loginCustomer.getPassword())){
				return new ModelAndView("profile", "cust", loginCustomer);
			}
			
			// If Passwords DON'T Match 
			else return new ModelAndView("login", "out_msg", "Password is Incorrect");

		}

		catch (Exception ex){
			// If Username is incorrect/doesn't exist in the Customer Table
			System.out.print("CustomerController:login: " + ex.getMessage());
			return new ModelAndView("login", "out_msg", "Username is Incorrect / Doesn't Exist");
		}
		
		finally {
			eMngr.close();
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

		/* TODO: Rewrite this Section
		 * 1) Check to see if Username already exists
		 * 2) If Not, Create new Account and go to Profile
		 * 3) If it does, Return to Register
		 * ** It has something to do with the connection open and close
		 * */
		
		
		// Try to find if username exists in Customer Table
		try {		
			eMngr.getTransaction().begin();
			Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
			Customer loginCustomer = (Customer) q_getByUsername.getSingleResult();
			System.out.print(loginCustomer.toString());
			throw new Exception("Found Existing");
		}
		
		catch (javax.persistence.NoResultException nre) {
			System.out.print("CustomerController:registerNew: " + nre.getMessage());
			eMngr.persist(cust);
			eMngr.getTransaction().commit();
			eMngr.close();
			return new ModelAndView("profile", "cust", cust);
		}
		
		catch (Exception ex){
			eMngr.close();
			System.out.print("CustomerController:registerNew: " + ex.getMessage());
			return new ModelAndView("register", "out_msg", "Username Already Exists! (From inside Old Generic Catch)");
		}
	}
	

	// FromMatt: NOT CURRENTLY IMPLEMENTED -> Need to figure out Session Variables
//	@RequestMapping(value="/profile", method=RequestMethod.GET)
//	public ModelAndView viewProfile() {
//		if(CustomerController.loggedInCustomer != null) {
//			return new ModelAndView("profile", "cust", CustomerController.loggedInCustomer);
//		}
//		else {
//			return new ModelAndView("login", "out_msg", "You must Log In First");
//		}
//	}
}
