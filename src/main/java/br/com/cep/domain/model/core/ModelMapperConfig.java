package br.com.cep.domain.model.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
		
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
