package com.g4m.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeanUtil {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
