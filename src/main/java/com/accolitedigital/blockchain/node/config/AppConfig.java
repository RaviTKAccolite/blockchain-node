package com.accolitedigital.blockchain.node.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class AppConfig {

  @Value("${notary.transaction.url}")
  String notaryInitTransactionEndpoint;

  @Value("${notary.validateMining.url}")
  String notaryValidateMiningEndpoint;

  @Value("${notary.miningRequest.url}")
  String notaryMiningRequestEndpoint;
}
