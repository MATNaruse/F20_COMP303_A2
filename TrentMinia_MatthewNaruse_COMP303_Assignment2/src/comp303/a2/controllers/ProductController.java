/*
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import comp303.a2.entities.Product;

@Controller
public class ProductController {
	private static EntityManagerFactory factory;
	private static EntityManager eMngr;

	private void init_EMF_EM() {
		// Instantiate New EntityManagerFactory and EntityManager
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}

	@GetMapping("/order")
	public ModelAndView mavDisplayPhones(HttpServletRequest request) {
		this.init_EMF_EM();
		ModelAndView order = ProductController.displayPhones();
		
		HttpSession session = request.getSession();
		order.addObject("cart", session.getAttribute("cart"));
		
		return order;
	}
	
	public static ModelAndView displayPhones() {
		ModelAndView productListMV = new ModelAndView("order");
		
		List<Product> productList = null;
		
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
		
		try {
			eMngr.getTransaction().begin();
			Query q_getAllProducts = eMngr.createQuery("Select e from Product e");
			productList = q_getAllProducts.getResultList();
			productListMV.addObject("products", productList);
			eMngr.close();
		}
		
		catch (Exception ex){
			System.out.println("ProductController:displayPhones: " + ex.getMessage());			
		}
		
		return productListMV;
	}
}
