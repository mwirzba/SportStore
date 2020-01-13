package ug.ShopApi;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ug.ShopApi.domain.Product;
import ug.ShopApi.repositories.ProductRepository;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan()
@Configuration
public class ShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApiApplication.class, args);
	}


	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

}
