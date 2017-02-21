package xyz.cafeconleche.web.training.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import xyz.cafeconleche.web.training.dev.dto.Greeting;
import xyz.cafeconleche.web.training.dev.service.GreetingService;


@RestController
@RequestMapping(path="/rest", produces = MediaType.APPLICATION_JSON_VALUE )
public class RestAccessController {
	
	@Autowired
	private GreetingService greetingService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/public")
	public @ResponseBody Greeting publico() throws JsonProcessingException {
		
		System.out.println("--- rest publico ---");
		Greeting greeting = greetingService.get("topi");
		return greeting;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/secure/admin")
	public @ResponseBody Greeting admin() throws JsonProcessingException {
		
		System.out.println("--- rest admin ---");
		Greeting greeting = greetingService.get("admin");
		return greeting;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/secure/dba")
	public @ResponseBody Greeting dba() throws JsonProcessingException {
		
		System.out.println("--- rest dba ---");
		Greeting greeting = greetingService.get("dba");
		return greeting;
	}
	
}
