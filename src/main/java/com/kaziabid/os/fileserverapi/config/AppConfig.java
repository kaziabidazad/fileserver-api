package com.kaziabid.os.fileserverapi.config;

import java.util.concurrent.Executor;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Kazi
 */
@Configuration
@EnableAsync
@EnableWebMvc
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "com.kaziabid.os.fileserverapi")
public class AppConfig {

    @Bean
    public Executor taskExecutor() {
	Executor taskExecutor = new ThreadPoolTaskExecutorBuilder().corePoolSize(5).maxPoolSize(10)
		.threadNamePrefix("Async-Task-").build();
	return taskExecutor;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
	return new WebMvcConfigurer() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			// .allowedHeaders("*", "Access-Control-Allow-Headers", "origin",
			// "Content-type", "accept",
			// "x-requested-with", "x-requested-by") // What is this for?
//			.allowCredentials(true);
	    }
	};
    }

//    @Configuration
//    public static class corsConfigurer implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//	    registry.addMapping("/").allowedOrigins("*").allowedMethods("*")
//		    // .allowedHeaders("*", "Access-Control-Allow-Headers", "origin",
//		    // "Content-type", "accept",
//		    // "x-requested-with", "x-requested-by") // What is this for?
//		    .allowCredentials(true);
//	}
//    }
}
