package com.orive.orive_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OriveWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriveWebsiteApplication.class, args);
	}

}
