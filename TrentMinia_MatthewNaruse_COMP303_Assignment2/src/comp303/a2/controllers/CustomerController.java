package comp303.a2.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
//	private static EntityManagerFactory factory;
//	private static EntityManager eMngr;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login() {
		return new ModelAndView("order");
	}
}
