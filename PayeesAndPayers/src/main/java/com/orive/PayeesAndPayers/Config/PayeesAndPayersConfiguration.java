package com.orive.PayeesAndPayers.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayeesAndPayersConfiguration {
	
	@Bean
	public ModelMapper  modelMapper() {
		return new ModelMapper();
		
	}

}
