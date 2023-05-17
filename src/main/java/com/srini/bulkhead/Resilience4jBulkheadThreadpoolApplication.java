package com.srini.bulkhead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Resilience4jBulkheadThreadpoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jBulkheadThreadpoolApplication.class, args);
	}

}
