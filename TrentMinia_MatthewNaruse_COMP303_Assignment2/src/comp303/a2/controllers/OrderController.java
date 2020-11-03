package comp303.a2.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

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
	
	private static Map<String, CartItem> cart;// = new HashMap<String, CartItem>();	
	
	private void initEMF_EM() {
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}
	
	/**
	 * [POST] Mapping for adding an Item to the Cart
	 * @param prod Product
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return order.jsp
	 */
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public ModelAndView addItem(@ModelAttribute("product") Product prod, HttpServletRequest request, HttpServletResponse response) {
		this.initEMF_EM();
		
		// Get product id and quantity of selected product
		int productId = Integer.parseInt(request.getParameter("product"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		ModelAndView updatedCartMV = ProductController.displayPhones();
		
		// Run query to get products
		try {
			eMngr.getTransaction().begin();
			Query q_getByProductId = eMngr.createQuery("Select e from Product e where e.productId = :eProductId").setParameter("eProductId", request.getParameter("product"));
			Product queryProduct = (Product) q_getByProductId.getSingleResult();
			String qProdName = queryProduct.getModelName();
			double qProdPrice = queryProduct.getPrice();
			eMngr.close();
			
			// Get Existing Cart from Session
			this.addCartItemToCart(request, qProdName, qProdPrice, quantity);
			
			updatedCartMV.addObject("cart", cart);

		} catch (Exception ex) {
			System.out.println("OrderController:addItem: " + ex.getMessage());
		}
		
		return updatedCartMV;
	}
	
	@RequestMapping(value="/remFromCart", method=RequestMethod.POST)
	public ModelAndView remFromCart(HttpServletRequest request) {
		ModelAndView updatedCart = ProductController.displayPhones();
		session = request.getSession();
		cart = (Map<String, CartItem>) session.getAttribute("cart");
		cart.remove(request.getParameter("removeItem"));
		session.setAttribute("cart", cart);
		
		updatedCart.addObject("cart", cart);
		return updatedCart;
		
	}
	
	@PostMapping("/confirmPayment")
	public ModelAndView confirmPayment(HttpServletRequest request) {
//		ModelAndView confirmationMV = new ModelAndView("confirm-order");
		ModelAndView confirmationMV = new ModelAndView("checkout");
		System.out.println(request.getParameter("deliveryDate"));
		Order ord = new Order();
		ord.isCancelable();
		
		this.refreshCart(request);
		
		confirmationMV.addObject("cart", cart);
		
		return confirmationMV;
	}
	
	/**
	 * Collects the cart from session, or creates it if it doesn't exist
	 * @param request HttpServletRequest
	 */
	private void refreshCart(HttpServletRequest request) {
		session = request.getSession();
		cart = (Map<String, CartItem>) session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<String, CartItem>();
			session.setAttribute("cart", cart);
		}
	}
	
	/**
	 * Adds a CartItem to the Cart
	 * @param request HttpServletRequest
	 * @param qProdName Name of Product
	 * @param qProdPrice Price of Individual Product
	 * @param quantity Quantity of Product
	 */
	private void addCartItemToCart(HttpServletRequest request, String qProdName, double qProdPrice, int quantity) {
		this.refreshCart(request);
		
		if(cart.containsKey(qProdName)) cart.get(qProdName).AddQuantity(quantity);
		else cart.put(qProdName, new CartItem(qProdName, qProdPrice, quantity));
		
		System.out.println(cart);
		session.setAttribute("cart", cart);
	}
}