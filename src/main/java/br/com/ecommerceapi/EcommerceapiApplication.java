package br.com.ecommerceapi;

import br.com.ecommerceapi.entity.Product;
import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.service.ProductService;
import br.com.ecommerceapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@SpringBootApplication
public class EcommerceapiApplication {


	public static void main(String[] args) {
		SpringApplication.run(EcommerceapiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService, UserService userService) {
		return args -> {
			userService.save(createUser());
			productService.save(new Product(1L, "TV", 300.00, "TV Smart 32"));
			productService.save(new Product(2L, "Game Console", 200.00, "Playstation 4"));
			productService.save(new Product(3L, "Sofa", 100.00, "Sofa"));
			productService.save(new Product(4L, "Pen", 5.00, "BIC"));
			productService.save(new Product(5L, "Book", 3.00, "Lord of the Rings"));
			productService.save(new Product(6L, "Phone", 500.00, "Smartphone Google"));
			productService.save(new Product(7L, "Watch", 30.00, "Apple Watch"));

		};
	}

	private User createUser() {
		User user = new User();
		user.setBirthday(LocalDate.of(2000, 1, 1));
		user.setEmail("fulano.email@gmail.com");
		user.setFirstName("Fulano");
		user.setLastName("Silva");
		user.setLogin("fulano");
		user.setPassword("$2a$10$.nr3AW0qy48YJqqeMTRDAOlztopZzpZZJ0PnPrwN5GxIjHxRWWHuC");
		user.setPhone("999999999");

		return user;
	}

}
