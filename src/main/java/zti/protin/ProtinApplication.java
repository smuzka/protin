package zti.protin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main class for the application
 */
@SpringBootApplication
public class ProtinApplication extends SpringBootServletInitializer {

	/**
	 * Main method for the application
	 *
	 * @param args - arguments for the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProtinApplication.class, args);
	}

}
