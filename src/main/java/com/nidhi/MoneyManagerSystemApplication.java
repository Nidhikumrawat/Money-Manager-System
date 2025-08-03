package com.nidhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class MoneyManagerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManagerSystemApplication.class, args);
	}

}
