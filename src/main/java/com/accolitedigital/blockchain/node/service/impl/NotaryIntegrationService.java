package com.accolitedigital.blockchain.node.service.impl;

import com.accolitedigital.blockchain.node.model.MessageInitializerRequestBody;
import com.accolitedigital.blockchain.node.model.MiningRequestAnswers;
import com.accolitedigital.blockchain.node.model.NotaryTransactionResponse;
import com.accolitedigital.blockchain.node.config.AppConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaryIntegrationService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  AppConfig appConfig;

  static Logger log = Logger.getLogger(NotaryIntegrationService.class.getName());

  public ResponseEntity<NotaryTransactionResponse> notaryInitTransaction(
      MessageInitializerRequestBody messageInitializerRequestBody, String nodeId)
      throws Exception {
    try {
      log.info("Calling init Transaction notary API");
      HttpEntity<MessageInitializerRequestBody> requestEntity =
          new HttpEntity<>(messageInitializerRequestBody, getBaseRequestHeaders(nodeId));
      return restTemplate.exchange(appConfig.getNotaryInitTransactionEndpoint(), HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<NotaryTransactionResponse>() {
          });
    } catch (Exception e) {
      log.info(" exception  occurred while invoking notary, exception message: " +
          e.getMessage());
      throw new Exception("Internal error");
    }
  }

  public ResponseEntity<Boolean> notaryValidateMining(
      MiningRequestAnswers miningRequestAnswers, String nodeId)
      throws Exception {
    try {
      log.info("Calling init Transaction notary API");
      HttpEntity<MiningRequestAnswers> requestEntity =
          new HttpEntity<>(miningRequestAnswers, getBaseRequestHeaders(nodeId));
      return restTemplate.exchange(appConfig.getNotaryValidateMiningEndpoint(), HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<Boolean>() {
          });
    } catch (Exception e) {
      log.info(" exception  occurred while invoking notary, exception message: " +
          e.getMessage());
      throw new Exception("Internal error");
    }
  }

  public ResponseEntity<String> notaryMiningRequest(String nodeId)
      throws Exception {
    try {
      log.info("Calling init Transaction notary API");
      HttpEntity<MiningRequestAnswers> requestEntity =
          new HttpEntity<>(getBaseRequestHeaders(nodeId));
      return restTemplate.exchange(appConfig.getNotaryMiningRequestEndpoint(), HttpMethod.POST,
          requestEntity,
          new ParameterizedTypeReference<String>() {
          });
    } catch (Exception e) {
      log.info(" exception  occurred while invoking notary, exception message: " +
          e.getMessage());
      throw new Exception("Internal error");
    }
  }

  private HttpHeaders getBaseRequestHeaders(String ndoeId) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add("node-id", ndoeId);
    return httpHeaders;
  }
}
