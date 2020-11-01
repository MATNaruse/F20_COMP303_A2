package comp303.a2.controllers;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import comp303.a2.entities.Product;

@Controller
public class ProductController {
	private static EntityManagerFactory factory;
	private static EntityManager eMngr;
	private static HttpSession session;

	private void init_EMF_EM() {
		// Instantiate New EntityManagerFactory and EntityManager
		factory = Persistence.createEntityManagerFactory("TrentMinia_MatthewNaruse_COMP303_Assignment2");
		eMngr = factory.createEntityManager();
	}
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public ModelAndView displayPhones() {
		ModelAndView productListMV = new ModelAndView("order");
		
		List<Product> productList = null;
		
		this.init_EMF_EM();
		
		try {
			eMngr.getTransaction().begin();
			Query q_getAllProducts = eMngr.createQuery("Select e from Product e");
			productList = q_getAllProducts.getResultList();
//			System.out.println(productList);
			productListMV.addObject("products", productList);
			for(Product prod: productList) {
				System.out.println(prod.getBrandName() + "|" + prod.getModelName() + "|" + prod.getPrice());
			}
			eMngr.close();
		}
		
		catch (Exception ex){
			System.out.println("ProductController:displayPhones: " + ex.getMessage());			
		}
		
		return productListMV;
	}
}
