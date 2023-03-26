package com.example.demo.service.impl;

import com.example.demo.config.AppConfig;
import com.example.demo.model.MessageInitializerRequestBody;
import com.example.demo.model.NotaryTransactionRequest;
import com.example.demo.model.NotaryTransactionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class NotaryIntegrationService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AppConfig appConfig;

  static Logger log = Logger.getLogger(NotaryIntegrationService.class.getName());

  public ResponseEntity<NotaryTransactionResponse> notaryInitTransaction(
      MessageInitializerRequestBody messageInitializerRequestBody)
      throws Exception {
    NotaryTransactionRequest notaryTransactionRequest = NotaryTransactionRequest.builder()
        .isValidTransaction(Boolean.TRUE)
        .acceptorName(messageInitializerRequestBody.getAcceptorName())
        .initializerName(messageInitializerRequestBody.getInitializerName())
        .message(messageInitializerRequestBody.getMessage())
        .build();
    try {
      HttpEntity<NotaryTransactionRequest> requestEntity =
          new HttpEntity<>(notaryTransactionRequest, getBaseRequestHeaders());
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

  private HttpHeaders getBaseRequestHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }
}
