package comp303.a2.controllers;

import java.text.ParseException;
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
	
	@PostMapping("/update-cart")
	public ModelAndView updateCart(HttpServletRequest request) {
		ModelAndView updatedCart = ProductController.displayPhones();
		
		this.getSessionCart(request);
		this.initEMF_EM();
		
		// If Removing
		if(request.getParameter("removeItem") != null) {
			String removeItemName = request.getParameter("removeItem");
			this.remCartItemFromCart(request, removeItemName);
		}
		
		// If Adding
		else if(request.getParameter("addItem") != null){
			// Get quantity of selected product
			int addItemId = Integer.parseInt(request.getParameter("addItem"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Product newProd = this.getProductById(addItemId);
			this.addCartItemToCart(request, newProd, quantity);
		}
		
		updatedCart.addObject("cart", cart);
		return updatedCart;
	}
	
	@GetMapping("/checkout")
	public ModelAndView checkout(HttpServletRequest request) {
		ModelAndView checkoutMV = new ModelAndView("checkout");
		
		this.getSessionCart(request);
		Customer cust = (Customer) session.getAttribute("currentCustomer");
		checkoutMV.addObject("cart", cart);
		checkoutMV.addObject("cust", cust);
		
		return checkoutMV;
	}
		
	@PostMapping("/confirmPayment")
	public ModelAndView confirmPayment(HttpServletRequest request) {
		ModelAndView confirmationMV = new ModelAndView("confirm-order");
		Boolean validDate = false;
		Boolean ccFilledOut = !request.getParameter("ccNumber").isEmpty()&&
								!request.getParameter("ccSecurity").isEmpty() &&
								!request.getParameter("ccName").isEmpty();
		
		// Delivery Date Validation
		if(!request.getParameter("deliveryDate").isEmpty()) {
			try {
				System.out.println(request.getParameter("deliveryDate"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dDate = sdf.parse(request.getParameter("deliveryDate"));
				Date now = new Date();
				int compareDate = dDate.compareTo(now);
				validDate = compareDate >= 0;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ccFilledOut && validDate) {
			// Initialize EMF and EM
			this.initEMF_EM();
			
			eMngr.getTransaction().begin(); // Start Transaction
			session = request.getSession(); // Refresh Session
			Customer currCustomer = (Customer) session.getAttribute("currentCustomer"); // Refresh CurrentCustomer for local use
			int custId = currCustomer.getCustId();
			
			this.getSessionCart(request);
			List<CartItem> finalCart = new ArrayList<CartItem>();
			finalCart.addAll(cart.values());
			
			// Setting up first item in order (if multiple)
			Date Now = new Date();
			SimpleDateFormat sdf_mysql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int oldOrderId = 0;
			if(session.getAttribute("modifyOrderId") != null) {
				oldOrderId = (Integer) session.getAttribute("modifyOrderId");
				Query q_getAllFromOrderId = eMngr.createQuery("Select e from Orders e where e.orderId = :eOrdId").setParameter("eOrdId", oldOrderId);
				List<Order> oldOrders = q_getAllFromOrderId.getResultList();
				for(Order ord: oldOrders) {
					eMngr.remove(ord);
				}
				eMngr.getTransaction().commit();
			}
			
			// Create the first order item
			Order newOrder = new Order();
			if(oldOrderId > 0) {
				newOrder.setOrderId(oldOrderId);
				eMngr.getTransaction().begin();
			}
			newOrder.setCustId(custId);
			newOrder.setProductId(finalCart.get(0).getProductId());
			newOrder.setQuantity(finalCart.get(0).getQuantity());
			newOrder.setDeliveryDate(request.getParameter("deliveryDate"));
			newOrder.setCreationDate(sdf_mysql.format(Now));
			newOrder.setOrderStatus("Processing...");
			
			eMngr.persist(newOrder);
	
			if(finalCart.size() > 1) {
				// If there are other items considered in this order, then get the last orderId to use
				int newOrderId = 0;
				Query q_getLastIndex = eMngr.createQuery("Select max(e.orderId) from Orders e where e.custId = :eCustId").setParameter("eCustId", custId);
				newOrderId = (Integer) q_getLastIndex.getSingleResult();
				System.out.println("LAST INDEX = " + newOrderId);
				for(CartItem cItem: finalCart) {
					if(finalCart.indexOf(cItem) != 0) {
						Order nextOrder = new Order();
						nextOrder.setOrderId(newOrderId);
						nextOrder.setCustId(custId);
						nextOrder.setProductId(cItem.getProductId());
						nextOrder.setQuantity(cItem.getQuantity());
						nextOrder.setDeliveryDate(request.getParameter("deliveryDate"));
						nextOrder.setCreationDate(sdf_mysql.format(Now));
						nextOrder.setOrderStatus("Processing...");
						eMngr.persist(nextOrder);
					}
				}
			}
			eMngr.getTransaction().commit();
			eMngr.close();
		}
		
		else {
			ModelAndView redoCheckout = new ModelAndView("checkout");
			if(!ccFilledOut) {
				redoCheckout.addObject("out_msg", "Please fill out All Credit card fields");
			}
			
			else if(!validDate) {
				redoCheckout.addObject("out_msg", "Please choose a valid Delivery Date");
			}
			this.getSessionCart(request);
			session.setAttribute("cart", cart);
			redoCheckout.addObject("cart", cart);
			
			Customer cust = (Customer) session.getAttribute("currentCustomer");
			redoCheckout.addObject("cust", cust);
			
			return redoCheckout;
		}
		
		
		this.getSessionCart(request);
		Map<String, CartItem> displayConfirmOrder = cart;
		
		session.setAttribute("cart", null);
		confirmationMV.addObject("cart", displayConfirmOrder);
		return confirmationMV;
	}
	
	@GetMapping("/confirm-order")
	public ModelAndView confirmOrderMV(HttpServletRequest request) {
		ModelAndView confirmationMV = new ModelAndView("confirm-order");
		this.initEMF_EM();
		this.getSessionCart(request);
		confirmationMV.addObject("cart", cart);
		session.setAttribute("cart", cart);
		return confirmationMV;
	}
	
	@PostMapping("/view-order")
	public ModelAndView displayOrder(HttpServletRequest request) {
		// Set up ModelAndView to Return
		ModelAndView viewOrderMV = new ModelAndView("view-order");
		
		// Collect Data
		int orderId = Integer.parseInt(request.getParameter("view-orderId"));
		this.initEMF_EM();
		eMngr.getTransaction().begin();
		session = request.getSession();
		Customer currCustomer = (Customer) session.getAttribute("currentCustomer");
		int custId = currCustomer.getCustId();
		
		List<Order> orderItems = this.organizeOrder(orderId, custId);
		List<CartItem> cartItems = new ArrayList<CartItem>();
		double orderTotal = 0.0;
		
		Order orderInfo = orderItems.get(0);
		orderInfo.isCancelable();
		for(Order order: orderItems) {
			int prodId = order.getProductId();
			Query q_getProdInfo = eMngr.createQuery("Select e from Product e where e.productId = :eProdId").setParameter("eProdId", prodId);
			Product qProd = (Product) q_getProdInfo.getSingleResult();
			
			CartItem cItem = new CartItem(qProd.getModelName(), prodId, qProd.getPrice(), order.getQuantity());
			orderTotal += cItem.getTotalPrice();
			cartItems.add(cItem);
		}
		
		eMngr.close();
		
		// Add Data to ModelAndView
		viewOrderMV.addObject("orderItems", cartItems);
		viewOrderMV.addObject("orderTotal", orderTotal);
		viewOrderMV.addObject("cust", currCustomer);
		viewOrderMV.addObject("orderInfo", orderInfo);
		
		return viewOrderMV;
	}
		
	@PostMapping("/modify-order")
	public ModelAndView modifyOrder(HttpServletRequest request) {
		ModelAndView modifyOrderMV = null;
		this.initEMF_EM();
		
		if(request.getParameter("modifyOrderId") != null) {
			// Modifying the Order
			modifyOrderMV = ProductController.displayPhones();
			int orderId = Integer.parseInt(request.getParameter("modifyOrderId"));
			session = request.getSession();
			session.setAttribute("modifyOrderId", orderId);
			Customer currCust = (Customer) session.getAttribute("currentCustomer");
			
			eMngr.getTransaction().begin();
			Query q_getOrdersByOrderIdCustId = eMngr.createQuery("Select e from Orders e where e.orderId = :eOrdId and e.custId = :eCustId")
					.setParameter("eOrdId", orderId).setParameter("eCustId", currCust.getCustId());
			List<Order> orders = q_getOrdersByOrderIdCustId.getResultList();
			
			Map<String, CartItem> orderCart = new HashMap<String, CartItem>();
			for(Order ord: orders) {
				Query q_getProductByProdId = eMngr.createQuery("Select e from Product e where e.productId = :eProdId").setParameter("eProdId", ord.getProductId());
				Product qProd = (Product) q_getProductByProdId.getSingleResult();
				CartItem newCItem = new CartItem(qProd.getModelName(), qProd.getProductId(), qProd.getPrice(), ord.getQuantity());	
				orderCart.put(qProd.getModelName(), newCItem);
			}
			
			cart = orderCart;
			
			session.setAttribute("cart", cart);
			modifyOrderMV.addObject("cart", cart);		
		}
		
		else if (request.getParameter("deleteOrder") != null) {
			// Deleting an Order
			eMngr.getTransaction().begin();
			int oldOrderId = Integer.parseInt(request.getParameter("deleteOrder"));
			Query q_getAllFromOrderId = eMngr.createQuery("Select e from Orders e where e.orderId = :eOrdId").setParameter("eOrdId", oldOrderId);
			List<Order> oldOrders = q_getAllFromOrderId.getResultList();
			for(Order ord: oldOrders) {
				eMngr.remove(ord);
			}
			eMngr.getTransaction().commit();
			modifyOrderMV = new ModelAndView("profile");
			session = request.getSession();
			Customer cust = (Customer) session.getAttribute("currentCustomer");
			modifyOrderMV.addObject("cust", cust);
			modifyOrderMV.addObject("out_msg", String.format("Order #%d Deleted!", oldOrderId));
			modifyOrderMV.addObject("ordersList", CustomerController.displayOrders(cust.getCustId()));
		}
		
		eMngr.close();
		return modifyOrderMV;

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

	private Product getProductById(int prodId) {
		Product queryProduct = null;
		try {
			eMngr.getTransaction().begin();
			Query q_getByProductId = eMngr.createQuery("Select e from Product e where e.productId = :eProductId")
										.setParameter("eProductId", prodId);
			queryProduct = (Product) q_getByProductId.getSingleResult();
			eMngr.close();			
		}
		
		catch (Exception ex) {
			System.out.println("OrderController:findProductById: " + ex.getMessage());
			eMngr.close();
		}
		return queryProduct;
	}
	
	// Cart Related Methods
	
	/**
	 * Collects the cart from session, or creates it if it doesn't exist
	 * @param request HttpServletRequest
	 */
	private void getSessionCart(HttpServletRequest request) {
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
		this.getSessionCart(request);
		
		if(cart.containsKey(qProdName)) cart.get(qProdName).AddQuantity(quantity);
		else cart.put(qProdName, new CartItem(qProdName, qProdId, qProdPrice, quantity));
		
//		System.out.println(cart);
		session.setAttribute("cart", cart);
	}
	
	/**
	 * Adds a CartItem to the Cart
	 * @param request HttpServletRequest
	 * @param prod Product to add
	 * @param quantity Quantity of Product
	 */
	private void addCartItemToCart(HttpServletRequest request, Product prod, int quantity) {
		this.getSessionCart(request);
		String prodName = prod.getModelName();
		int prodId = prod.getProductId();
		double prodPrice = prod.getPrice();
		if(cart.containsKey(prodName)) cart.get(prodName).AddQuantity(quantity);
		else cart.put(prodName, new CartItem(prodName, prodId, prodPrice, quantity));

		session.setAttribute("cart", cart);
	}
	
	/**
	 * Removes a CartItem from the Cart
	 * @param request HttpServletRequest
	 * @param qProdName Name of Product to remove
	 */
	private void remCartItemFromCart(HttpServletRequest request, String qProdName) {
		this.getSessionCart(request);
		cart.remove(qProdName);
		session.setAttribute("cart", cart);		
	}
}