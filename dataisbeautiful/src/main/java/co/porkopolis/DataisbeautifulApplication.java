package co.porkopolis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DataisbeautifulApplication {

	private static final Logger log = LoggerFactory.getLogger(DataisbeautifulApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DataisbeautifulApplication.class, args);
//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}

	}

}
