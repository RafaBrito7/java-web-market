package com.devinhouse.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class GsonConfig {

	@Bean
	public Gson getGson() {
//		return new Gson();
		return new GsonBuilder().create();
	}
}
