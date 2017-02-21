package xyz.cafeconleche.web.training.dev.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import xyz.cafeconleche.web.training.dev.dao.SecurityDao;

@Repository
public class SecurityDaoCassandraImpl implements SecurityDao {

	@Autowired
	private CassandraAdminOperations cassandraTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("--- loadUserByUsername ---");
		
		xyz.cafeconleche.web.training.dev.dto.User userResult = getUser(username);
		
		if (userResult == null) {
			throw new UsernameNotFoundException("user: " + username + " not found");
		}

		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(username);
		System.out.println("grantedAuthorities (" + username + "): " + grantedAuthorities);
		System.out.println("cassandraTemplate: " + cassandraTemplate);
		
		UserDetails user = populateUser(userResult, grantedAuthorities);
        return user;
	}
	
	private UserDetails populateUser(xyz.cafeconleche.web.training.dev.dto.User userResult,
			List<GrantedAuthority> authorities) {
		
		boolean enabled = true;
		boolean accountNotExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserDetails user = new User(userResult.getUsername(), userResult.getPassword(), enabled, accountNotExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
	
	private xyz.cafeconleche.web.training.dev.dto.User getUser(String username) {

		Statement selectStatement = QueryBuilder.select().from("user_roles")
				.where(QueryBuilder.eq("username", username))
				.limit(1);
		
		xyz.cafeconleche.web.training.dev.dto.User user = cassandraTemplate.selectOne(
				selectStatement, xyz.cafeconleche.web.training.dev.dto.User.class);

		System.out.println("user: " + user);
		return user;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(String username) {
		
		Statement selectStatement = QueryBuilder.select("role").from("user_roles")
				.where(QueryBuilder.eq("username", username));
		
		List<String> roles = cassandraTemplate.select(selectStatement, String.class);
		System.out.println("*** roles: " + roles);
		
		if (roles != null && !roles.isEmpty()) {
			
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); 
			roles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r)));
			return new ArrayList<GrantedAuthority>(grantedAuthorities);
		}
		
		return null;
	}

}
