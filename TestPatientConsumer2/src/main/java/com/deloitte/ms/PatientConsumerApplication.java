package com.deloitte.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PatientConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientConsumerApplication.class, args);
	}

}
