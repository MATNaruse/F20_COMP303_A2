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

import comp303.a2.entities.Customer;
import comp303.a2.entities.Order;
import comp303.a2.models.CartItem;

@Controller
public class OrderController {
	private static HttpSession session;
	private static List<CartItem> cart = new ArrayList<CartItem>();
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response) {
		String productName = request.getParameter("productName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double initialPrice = Double.parseDouble(request.getParameter("initialPrice"));
		
		//Final price is dictated by quantity
		double price = quantity * initialPrice;
		
		// Instantiate objects
		ModelAndView mav = new ModelAndView();
		CartItem item = new CartItem();
		item.setProductName(productName);
		item.setQuantity(quantity);
		item.setPrice(price);
		
		cart.add(item);
		
		mav.addObject(cart);
		
		return mav;
	}
}