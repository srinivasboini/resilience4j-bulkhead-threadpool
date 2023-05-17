package com.srini.bulkhead;

import com.srini.bulkhead.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class Resilience4jBulkheadThreadpoolApplication {

	private final AppService appService ;


	@EventListener(ApplicationStartedEvent.class)
	public void execute(){
		IntStream.rangeClosed(1,20)
				.parallel()
				.forEach(i -> appService.get());
	}
	public static void main(String[] args) {
		SpringApplication.run(Resilience4jBulkheadThreadpoolApplication.class, args);
	}

}
