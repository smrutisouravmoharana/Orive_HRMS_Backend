package com.orive.HrmServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class HrmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmGatewayApplication.class, args);
	}

}
