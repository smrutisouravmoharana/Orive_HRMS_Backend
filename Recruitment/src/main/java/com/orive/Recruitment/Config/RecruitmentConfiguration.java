package com.orive.Recruitment.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecruitmentConfiguration {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
