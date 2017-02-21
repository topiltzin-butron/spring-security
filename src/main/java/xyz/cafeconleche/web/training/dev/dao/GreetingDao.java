package xyz.cafeconleche.web.training.dev.dao;

import xyz.cafeconleche.web.training.dev.dto.Greeting;

public interface GreetingDao {

	Greeting get(String user);
	
}
