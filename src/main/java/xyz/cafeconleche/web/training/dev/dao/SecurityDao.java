package xyz.cafeconleche.web.training.dev.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityDao {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
