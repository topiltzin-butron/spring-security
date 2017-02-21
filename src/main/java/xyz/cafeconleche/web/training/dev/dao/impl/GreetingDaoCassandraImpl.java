package xyz.cafeconleche.web.training.dev.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import xyz.cafeconleche.web.training.dev.dao.GreetingDao;
import xyz.cafeconleche.web.training.dev.dto.Greeting;

@Repository
public class GreetingDaoCassandraImpl implements GreetingDao {

	@Autowired
	private CassandraAdminOperations cassandraTemplate;
//	private CassandraTemplate cassandraTemplate;
	
	@Override
	public Greeting get(String user) {
		
		Statement select = QueryBuilder.select()
				.from("greetings")
				.where(QueryBuilder.eq("user", user));
		
		Greeting greeting = cassandraTemplate.selectOne(select, Greeting.class);
		return greeting;
	}

}
