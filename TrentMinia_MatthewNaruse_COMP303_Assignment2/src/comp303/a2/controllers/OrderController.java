package comp303.a2.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
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
	
	 // Mapping Methods

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
			Query q_getByProductId = eMngr.createQuery("Select e from Product e where e.productId = :eProductId")
										.setParameter("eProductId", Integer.parseInt(request.getParameter("product")));
			Product queryProduct = (Product) q_getByProductId.getSingleResult();
			String qProdName = queryProduct.getModelName();
			double qProdPrice = queryProduct.getPrice();
			int qProdId = queryProduct.getProductId();
			eMngr.close();
			
			// Get Existing Cart from Session
			this.addCartItemToCart(request, qProdName, qProdId, qProdPrice, quantity);
			
			updatedCartMV.addObject("cart", cart);

		} catch (Exception ex) {
			System.out.println("OrderController:addItem: " + ex.getMessage());
		}
		
		return updatedCartMV;
	}
	
	@GetMapping("/confirm-order")
	public ModelAndView confirm_order(HttpServletRequest request) {
		ModelAndView confirmationMV = new ModelAndView("confirm-order");
		this.initEMF_EM();
		this.refreshCart(request);
		confirmationMV.addObject("cart", cart);
		session.setAttribute("cart", cart);
		return confirmationMV;
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
		ModelAndView confirmationMV = new ModelAndView("confirm-order");
//		ModelAndView confirmationMV = new ModelAndView("checkout");
//		System.out.println(request.getParameter("deliveryDate"));
		
		Boolean correct_data = true;
		
		if(correct_data) {
			this.initEMF_EM();
			eMngr.getTransaction().begin();
			session = request.getSession();
			Customer currCustomer = (Customer) session.getAttribute("currentCustomer");
			int custId = currCustomer.getCustId();
			this.refreshCart(request);
			List<CartItem> finalCart = new ArrayList<CartItem>();
			finalCart.addAll(cart.values());
			// Setting up first item in order (if multiple)
			Date Now = new Date();
			SimpleDateFormat sdf_mysql = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
			
			// Create the first order item
			Order newOrder = new Order();
			newOrder.setCustId(custId);
			newOrder.setProductId(finalCart.get(0).getProductId());
			newOrder.setQuantity(finalCart.get(0).getQuantity());
			newOrder.setDeliveryDate(request.getParameter("deliveryDate"));
			newOrder.setCreationDate(sdf_mysql.format(Now));
			newOrder.setOrderStatus("Processing...");
			
			eMngr.persist(newOrder);
			eMngr.getTransaction().commit();
			
			
			if(finalCart.size() > 1) {
				// If there are other items considered in this order, then get the last orderId to use
				Query q_getLastIndex = eMngr.createQuery("Select max(e.orderId) from Orders e where e.custId = :eCustId").setParameter("eCustId", custId);
				int newOrderId = (Integer) q_getLastIndex.getSingleResult();
				System.out.println("LAST INDEX = " + newOrderId);
				
				for(CartItem cItem: finalCart) {
					if(finalCart.indexOf(cItem) != 0) {
						eMngr.getTransaction().begin();
						Order nextOrder = new Order();
						nextOrder.setOrderId(newOrderId);
						nextOrder.setCustId(custId);
						nextOrder.setProductId(cItem.getProductId());
						nextOrder.setQuantity(cItem.getQuantity());
						nextOrder.setDeliveryDate(request.getParameter("deliveryDate"));
						nextOrder.setCreationDate(sdf_mysql.format(Now));
						nextOrder.setOrderStatus("Processing...");
						eMngr.persist(nextOrder);
						eMngr.getTransaction().commit();
					}
				}
			}
			eMngr.close();
//			return new ModelAndView("confirm-order");
		}
		
		this.refreshCart(request);
	
		
		return confirmationMV;
	}
	
	@PostMapping("/view-order")
	public ModelAndView displayOrder(HttpServletRequest request) {
		ModelAndView viewOrderMV = new ModelAndView("view-order");
		int orderId = Integer.parseInt(request.getParameter("view-orderId"));
		
		this.initEMF_EM();
		eMngr.getTransaction().begin();
		session = request.getSession();
		Customer currCustomer = (Customer) session.getAttribute("currentCustomer");
		int custId = currCustomer.getCustId();
		
		List<Order> orderItems = this.organizeOrder(orderId, custId);
		List<CartItem> cartItems = new ArrayList<CartItem>();
		
		
		for(Order order: orderItems) {
			int prodId = order.getProductId();
			Query q_getProdInfo = eMngr.createQuery("Select e from Product e where e.productId = :eProdId").setParameter("eProdId", prodId);
			Product qProd = (Product) q_getProdInfo.getSingleResult();
			cartItems.add(new CartItem(qProd.getModelName(), prodId, qProd.getPrice(), order.getQuantity()));
		}
		
		eMngr.close();
		viewOrderMV.addObject("orderItems", cartItems);
		
		return viewOrderMV;
	}
	
	
	// Private Methods
	
	/**
	 * Initializes new EntitiyManagerFactory and EntityManager
	 */
	private void initEMF_EM() {
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}
	
	
	private List<Order> organizeOrder(int orderId, int custId) {
//		eMngr.getTransaction().begin();
		Query q_getOrdersByCompKey = eMngr.createQuery("Select e from Orders e where e.orderId = :eOrderId and e.custId = :eCustId")
				.setParameter("eOrderId", orderId).setParameter("eCustId", custId);
		List<Order> orders = q_getOrdersByCompKey.getResultList();
//		eMngr.close();
		return orders;
	}
	
	
	// Cart Related Methods
	
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
	private void addCartItemToCart(HttpServletRequest request, String qProdName, int qProdId, double qProdPrice, int quantity) {
		this.refreshCart(request);
		
		if(cart.containsKey(qProdName)) cart.get(qProdName).AddQuantity(quantity);
		else cart.put(qProdName, new CartItem(qProdName, qProdId, qProdPrice, quantity));
		
//		System.out.println(cart);
		session.setAttribute("cart", cart);
	}
}