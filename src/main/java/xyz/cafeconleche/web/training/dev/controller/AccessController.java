package xyz.cafeconleche.web.training.dev.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessController {
	
	@RequestMapping("/public")
	public ModelAndView publico(){
		System.out.println("--- public ---");
		ModelAndView mav = new ModelAndView("public");
		mav.addObject("date", new Date());
		return mav;
	}
	
	@RequestMapping("/secure/admin")
	public ModelAndView admin(){
		System.out.println("--- secure/admin ---");
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("date", new Date());
		return mav;
	}
	
	@RequestMapping("/secure/dba")
	public ModelAndView home(){
		System.out.println("--- secure/dba ---");
		ModelAndView mav = new ModelAndView("dba");
		mav.addObject("date", new Date());
		return mav;
	}

}
