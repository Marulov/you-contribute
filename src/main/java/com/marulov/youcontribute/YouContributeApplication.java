package com.marulov.youcontribute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YouContributeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouContributeApplication.class, args);
	}

}
