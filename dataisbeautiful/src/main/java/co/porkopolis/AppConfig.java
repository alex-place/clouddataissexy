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
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;

@Configuration
public class AppConfig {

	public final String API_KEY = "7d62f8ef-aceb-4d1d-b2ba-9f7946f0aa29";

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

	@Bean
	public RiotApi getApi() {

		ApiConfig config = new ApiConfig();
		config.setKey(API_KEY);
		RiotApi api = new RiotApi(config);

		return api;

	}

}