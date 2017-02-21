package xyz.cafeconleche.web.training.dev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.cafeconleche.web.training.dev.dao.GreetingDao;
import xyz.cafeconleche.web.training.dev.dto.Greeting;
import xyz.cafeconleche.web.training.dev.service.GreetingService;

@Service
public class GreetingServicePojoImpl implements GreetingService {

	@Autowired
	private GreetingDao greetingDao;
	
	@Override
	public Greeting get(String user) {
		return greetingDao.get(user);
	}

}
