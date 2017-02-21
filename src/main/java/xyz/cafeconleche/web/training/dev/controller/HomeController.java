package xyz.cafeconleche.web.training.dev.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public ModelAndView home(){
		System.out.println("--- home ---");
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("date", new Date());
		return mav;
	}
	
}
