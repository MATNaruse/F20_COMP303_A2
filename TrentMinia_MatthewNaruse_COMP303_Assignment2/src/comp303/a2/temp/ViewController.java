package comp303.a2.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	@GetMapping("/order")
	public ModelAndView order() {
		return new ModelAndView("order");
	}
	
	@GetMapping("/checkout")
	public ModelAndView checkout() {
		return new ModelAndView("checkout");
	}
	
	@GetMapping("/confirm-order")
	public ModelAndView confirm_order() {
		return new ModelAndView("confirm-order");
	}
	
	@GetMapping("/profile")
	public ModelAndView profile() {
		return new ModelAndView("profile");
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("register");
	}
	
	@GetMapping("/view-order")
	public ModelAndView view_order() {
		return new ModelAndView("view-order");
	}
}
