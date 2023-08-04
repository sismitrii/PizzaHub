package fr.eni.pizzaHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;

//import fr.eni.pizzaHub.config.RsaKeyProperties;

@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperties.class)
public class PizzaHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaHubApplication.class, args);
	}

}
