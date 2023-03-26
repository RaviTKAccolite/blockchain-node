package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class AppConfig {

  @Value("${node.auth.token}")
  String authToken;

  @Value("${notary.transaction.url}")
  String notaryInitTransactionEndpoint;
}
