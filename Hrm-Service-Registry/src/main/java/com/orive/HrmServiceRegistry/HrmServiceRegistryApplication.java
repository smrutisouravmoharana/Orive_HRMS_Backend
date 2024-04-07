package com.orive.HrmServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HrmServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmServiceRegistryApplication.class, args);
	}

}
