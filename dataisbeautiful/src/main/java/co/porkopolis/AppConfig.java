package co.porkopolis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import co.porkopolis.dao.BasicSummonerDAO;
import co.porkopolis.dao.impl.JDBCBasicSummonerDAOImpl;
import co.porkopolis.requests.Request;

@Configuration
public class AppConfig {

	@Bean
	public ViewResolver viewResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("LEGACYHTML5");
		templateResolver.setCacheable(false);
		// templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(engine);
		return viewResolver;
	}

	@Bean
	public BasicSummonerDAO basicSummonerDAO() {
		return new JDBCBasicSummonerDAOImpl();
	}

	@Bean
	public Request request() {
		return new Request();
	}

}