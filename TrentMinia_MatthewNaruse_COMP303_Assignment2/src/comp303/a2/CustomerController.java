package comp303.a2;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("customer")Customer customer, BindingResult result, ModelMap model) {
		return new ModelAndView("order");
	}
}
