/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import comp303.a2.entities.Order;

@Controller
public class CustomerController {	
	private static EntityManagerFactory factory;
	private static EntityManager eMngr;
	private static HttpSession session;
	
	private void initEMF_EM() {
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}
	
	/**
	 * [GET] Mapping for an Empty Login Page
	 * @return Login Page, with empty Customer Entity
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView preplogin() {
		// Returns Login page with Empty Customer Model
		return new ModelAndView("login", "customer", new Customer());
	}
	
	/**
	 * [POST] Mapping for Trying to Login
	 * @param cust ModelAttribute of type Customer
	 * @param result BindingResult
	 * @param model ModelMap
	 * @param request HttpServletRequest
	 * @return Success: Profile Page | Fail: Login Page
	 */
	@RequestMapping(value="/trylogin", method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("customer") Customer cust, BindingResult result, ModelMap model, HttpServletRequest request) {
		
		// Checks for errors first
		if (result.hasErrors()) return null;
		
		// Instantiate New EntityManagerFactory and EntityManager
//		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
//		eMngr = factory.createEntityManager();
		this.initEMF_EM();
		
		try {
			// Find the Customer from Table using Username
			eMngr.getTransaction().begin();
			Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
			Customer loginCustomer = (Customer) q_getByUsername.getSingleResult();
			eMngr.close();
			
			// Check to see if Passwords Match
			if(cust.getPassword().equals(loginCustomer.getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("currentCustomer", loginCustomer);
				ModelAndView profileMV = new ModelAndView("profile", "cust", loginCustomer);
				profileMV.addObject("ordersList", this.displayOrders(loginCustomer.getCustId()));
				return profileMV;
			}
			
			// If Passwords DON'T Match 
			else return new ModelAndView("login", "out_msg", "Password is Incorrect");

		}
		
		catch (javax.persistence.NoResultException nre) {
			// If Username is incorrect/doesn't exist in the Customer Table
			System.out.print("CustomerController:login: " + nre.getMessage());
			return new ModelAndView("login", "out_msg", "Username is Incorrect / Doesn't Exist)");
		}

		catch (Exception ex){
			// Catching any other Exception
			System.out.print("CustomerController:login: " + ex.getMessage());
			return new ModelAndView("login", "out_msg", "Unexpected Error: " + ex.getMessage());
		}
	}
	
	/**
	 * [GET] Mapping for Basic Action to Log out
	 * @param request HttpServletRequest
	 * @return Returns to Login Page, with success message
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)	
	public ModelAndView logout(HttpServletRequest request) {
		// Ensuring we have session data
		session = request.getSession();
		
		// If we have a logged in customer, remove them from session
		if(session.getAttribute("currentCustomer") != null) session.removeAttribute("currentCustomer");
		if(session.getAttribute("cart") != null) session.removeAttribute("cart");
		ModelAndView loggedOut = this.preplogin();
		loggedOut.addObject("out_msg", "You have successfully logged out!");
		return loggedOut;
	}
	
	
	/**
	 * [GET] Mapping for Empty Register Page
	 * @return Register Page, with Empty Customer Entity
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView freshRegister() {
		// Returns Register Page with empty Customer
		return new ModelAndView("register", "customer", new Customer());
	}
	
	/**
	 * [POST] Mapping for Registering a New Customer
	 * @param cust ModelAttribute, of type Customer
	 * @param result BindingResult
	 * @param model ModelMap
	 * @param request HttpServletRequest
	 * @return Success: Profile Page | Fail: Register Page
	 */
	@RequestMapping(value="newregister", method=RequestMethod.POST)
	public ModelAndView registerNew(@Valid @ModelAttribute("customer") Customer cust, BindingResult result, ModelMap model, HttpServletRequest request) {
		// Checks for errors first
		if (result.hasErrors()) return null;
		
		// TODO: I don't know if this model is actually needed
		model.addAttribute("customer", cust); 
		
		// Instantiate New EntityManagerFactory and EntityManager
//		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
//		eMngr = factory.createEntityManager();
		this.initEMF_EM();
		session = request.getSession();
		
		
		try {
			// Try to find if user name exists in Customer Table		
			eMngr.getTransaction().begin();
			Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
			Customer loginCustomer = (Customer) q_getByUsername.getSingleResult();
			System.out.println(loginCustomer.toString());
			throw new Exception("Found Existing");
		}
		
		catch (javax.persistence.NoResultException nre) {
			// Catches error if query doesn't find a match -> Meaning Available User name
			System.out.println("CustomerController:registerNew: " + nre.getMessage());
			System.out.println("CustomerController:registerNew: SAVING NEW CUSTOMER");
			
			// Save new Customer to Table
			eMngr.persist(cust);
			eMngr.getTransaction().commit();
			
			Query q_getByUsername = eMngr.createQuery("Select e from Customer e where e.userName like :eUserName").setParameter("eUserName", cust.getUserName());
			Customer regCustomer = (Customer) q_getByUsername.getSingleResult();
			
			eMngr.close();
			
			// Setting Newly Registered Customer as Logged In
			session.setAttribute("currentCustomer", regCustomer);
			session.setAttribute("cart", null);
			return new ModelAndView("profile", "cust", cust);
		}
		
		catch (Exception ex){
			// Catches any exception, including user-defined "Found Existing"
			eMngr.close();
			System.out.println("CustomerController:registerNew: " + ex.getMessage());
			if(ex.getMessage().equals("Found Existing")) return new ModelAndView("register", "out_msg", "Username Already Exists! (From inside Old Generic Catch)");
			else return new ModelAndView("register", "out_msg", "Unknown Error! (From inside Old Generic Catch)");
		}
	}
	

	/**
	 * [GET] Mapping for Profile Page
	 * @param model Model
	 * @param request HttpServletRequest
	 * @return Success: Profile Page | Fail: Login Page
	 */
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView viewProfile(Model model, HttpServletRequest request) {
		//TODO: I don't exactly know if Model is still needed here again...
		
		session = request.getSession();
		
		// If there is a Customer logged in
		if(session.getAttribute("currentCustomer") != null) {
			
			Customer currCustOBJ = (Customer) session.getAttribute("currentCustomer");
			ModelAndView currCustMV = new ModelAndView("profile", "cust", currCustOBJ);

			// Get Current Customer's Orders, if any
			List<Order> ordersList = this.displayOrders(currCustOBJ.getCustId());
			if (ordersList != null) {
				System.out.println(ordersList);
				currCustMV.addObject("ordersList", ordersList);
			}
			return currCustMV;
		}
		
		// If not logged in, then prompt them
		// TODO: This has become a backup safety, as UI option to view profile while not logged in isn't possible.
		else {
			ModelAndView login_prompt = this.preplogin();
			login_prompt.addObject("out_msg", "You need to log in first");
			return login_prompt;
		}
	}
	
	
	@RequestMapping(value="/save-profile", method=RequestMethod.POST)
	public ModelAndView saveEditedProfile(@Valid @ModelAttribute("customer") Customer cust, BindingResult result, ModelMap model, HttpServletRequest request) {
		
		this.initEMF_EM();
		session=request.getSession();
		
		int custId = (int) ((Customer) session.getAttribute("currentCustomer")).getCustId();
		
		eMngr.getTransaction().begin();
		Customer currCustOBJ = eMngr.find(Customer.class, custId);
		
		currCustOBJ.setPassword(request.getParameter("password"));
		currCustOBJ.setFirstname(request.getParameter("firstname"));
		currCustOBJ.setLastname(request.getParameter("lastname"));
		currCustOBJ.setAddress(request.getParameter("address"));
		currCustOBJ.setCity(request.getParameter("city"));
		currCustOBJ.setCountry(request.getParameter("country"));
		currCustOBJ.setPostalCode(request.getParameter("postalCode"));

		eMngr.persist(currCustOBJ);
		eMngr.getTransaction().commit();
		eMngr.close();
		
		ModelAndView updatedProfileMV = new ModelAndView("profile", "cust", currCustOBJ);
		updatedProfileMV.addObject("out_msg", "Profile Updated Successfully!");
		List<Order> ordersList = this.displayOrders(currCustOBJ.getCustId());

		updatedProfileMV.addObject("ordersList", ordersList);
		
		return updatedProfileMV;
	}
	/**
	 * Get a list of Orders, by Customer ID
	 * @param custId Customer's ID
	 * @return List<Order> of all Orders by Customer
	 */
	private List<Order> displayOrders(int custId) {
		List<Order> ordersList = null;
//		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
//		eMngr = factory.createEntityManager();
		this.initEMF_EM();
		
		try {		
			eMngr.getTransaction().begin();
			Query q_getAllOrdersByCustId = eMngr.createQuery("Select e from Orders e where e.custId = :eCustId").setParameter("eCustId", custId);
			ordersList = q_getAllOrdersByCustId.getResultList();
			eMngr.close();
		}
		
		catch (javax.persistence.NoResultException nre) {
			// Catches error if customer has no orders
			System.out.println("CustomerController:displayOrders: " + nre.getMessage());
			eMngr.close();
		}

		if (ordersList != null) {
			List<Order> filteredList = new ArrayList<Order>();
			Order prevOrd = null;
			for(Order ord : ordersList) {
				if(prevOrd == null) {
					prevOrd = ord;
					filteredList.add(ord);
				}
				else if (prevOrd.getOrderId() != ord.getOrderId()) {
					filteredList.add(ord);
					prevOrd = ord;
				}
			}
			return filteredList;
		}
		return ordersList;


	}
	
	
	
}
