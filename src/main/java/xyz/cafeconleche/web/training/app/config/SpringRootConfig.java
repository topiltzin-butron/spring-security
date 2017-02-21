package xyz.cafeconleche.web.training.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "xyz.cafeconleche.web.training.*", 
	"xyz.cafeconleche.web.training.dev.service.*",
	"xyz.cafeconleche.web.training.dev.dao.*"
	 })
public class SpringRootConfig {

}
