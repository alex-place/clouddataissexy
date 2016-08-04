package co.porkopolis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DataisbeautifulApplication {

	private static final Logger log = LoggerFactory.getLogger(DataisbeautifulApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DataisbeautifulApplication.class, args);

	}

}
