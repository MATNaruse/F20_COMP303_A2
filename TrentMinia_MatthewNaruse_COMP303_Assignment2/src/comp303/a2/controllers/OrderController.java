package comp303.a2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import comp303.a2.controllers.ProductController;
import comp303.a2.entities.Customer;
import comp303.a2.entities.Order;
import comp303.a2.entities.Product;
import comp303.a2.models.CartItem;

@Controller
public class OrderController {
	private static HttpSession session;
	
	private static EntityManagerFactory factory;
	private static EntityManager eMngr;
	
	private static List<Product> cart = new ArrayList<Product>();
	private static List<String> cartItems = new ArrayList<String>();
	
	private void initEMF_EM() {
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}
	
	// Modify as "/order" RequestMethod.POST to update Cart
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public ModelAndView addItem(@ModelAttribute("product") Product prod, HttpServletRequest request, HttpServletResponse response) {
		this.initEMF_EM();
		
		// Get product id and quantity of selected product
		int productId = Integer.parseInt(request.getParameter("product"));
		System.out.println(productId);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println(quantity);
		
		// Run query to get products
		try {
			eMngr.getTransaction().begin();
			Query q_getByProductId = eMngr.createQuery("Select e from Product e where e.productId = :eProductId").setParameter("eProductId", request.getParameter("product"));
			Product queryProduct = (Product) q_getByProductId.getSingleResult();
			
			String tempName = queryProduct.getModelName();
			eMngr.close();
			
			// Price depends on quantity
			double price = quantity * queryProduct.getPrice();
			System.out.println(String.format("%s - Price: %f", tempName, price));
			
			
		} catch (Exception ex) {
			System.out.println("OrderController:addItem: " + ex.getMessage());
			//return new ModelAndView("order", "out_msg", "Unexpected Error: " + ex.getMessage());
		}
		
		//return new ModelAndView("order");
		ProductController pc = new ProductController();
		
		ModelAndView rebuildOrderMV = pc.displayPhones();
		return rebuildOrderMV;
	}
	
	// private list display cart method goes here
}