package xyz.cafeconleche.web.training.dev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.cafeconleche.web.training.dev.dao.SecurityDao;
import xyz.cafeconleche.web.training.dev.service.SecurityService;

@Service
public class SecurityServicePojoImpl implements SecurityService {

	@Autowired
	private SecurityDao securityDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return securityDao.loadUserByUsername(username);
	}

}
