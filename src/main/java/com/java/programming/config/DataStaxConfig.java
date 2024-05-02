package com.java.programming.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Path;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.cassandra")
public class DataStaxConfig {

    private File secureConnectBundle;

    @Bean
    public CqlSessionBuilderCustomizer cqlSessionBuilderCustomizer(){
        Path bundle = secureConnectBundle.toPath();
        return cqlSessionBuilder -> cqlSessionBuilder.withCloudSecureConnectBundle(bundle);
    }

}
