package com.java.programming;

import com.java.programming.config.DataStaxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxConfig.class)
public class FileProcessorApplication {

	public static void main(String[] args) {

		SpringApplication.run(FileProcessorApplication.class, args);

	}

}
