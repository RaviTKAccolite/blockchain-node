package com.example.demo.service.impl;

import com.example.demo.config.AppConfig;
import com.example.demo.entity.NodeConfiguration;
import com.example.demo.model.MessageInitializerRequestBody;
import com.example.demo.model.Nodes;
import com.example.demo.model.NotaryTransactionResponse;
import com.example.demo.service.NodeService;
import com.example.demo.service.util.JsonReader;
import com.example.demo.service.util.NotaryIntegrationUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {

  private static final String NodeServiceImpl = "NodeServiceImpl";

  static Logger log = Logger.getLogger(NodeServiceImpl.class.getName());
  @Autowired
  JsonReader jsonReader;

  @Autowired
  AppConfig appConfig;

  @Autowired
  NotaryIntegrationService notaryIntegrationService;

  @Autowired
  NotaryIntegrationUtil notaryIntegrationUtil;

  @Override
  public void messageInitializer(String authHeader, MessageInitializerRequestBody requestBody)
      throws Exception {
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerName()
        + "; message to be received by : " + requestBody.getAcceptorName()
        + "; with message : " + requestBody.getMessage());
    if (Boolean.TRUE.equals(isAuthValid(authHeader))) {
      //  Proceed calling notary.
      ResponseEntity<NotaryTransactionResponse> notaryTransactionResponseEntity = notaryIntegrationService.notaryInitTransaction(
          requestBody);
      NotaryTransactionResponse notaryTransactionResponse = notaryTransactionResponseEntity.getBody();

    } else {
      log.info(
          NodeServiceImpl + "Node with id: " + requestBody.getInitializerName() + " is not valid");
    }

  }

  private void invokeNotaryInitTransaction() {

  }

  @Override
  public void transactionValidation(MessageInitializerRequestBody requestBody) throws Exception {
    Nodes nodesList = jsonReader.jsonToObject("/NodesList.json", Nodes.class);
    if (!nodesList.getNodes().contains(requestBody.getInitializerName())) {
      log.info("error");
    }
    if (requestBody.getAcceptorName().equals("current node id")) {
      //TODO
      messageAcceptor(requestBody);
    }
  }

  @Override
  public List<String> miningRequest() {
    JSONObject questionnaire = jsonReader.jsonToObject("/Questionnaire.json", JSONObject.class);

    return null;
  }

  private void messageAcceptor(MessageInitializerRequestBody requestBody) throws Exception {
    // TODO decrypting the data transferred
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerName()
        + "; message is received by : " + requestBody.getAcceptorName()
        + "; with message : " + requestBody.getMessage());
    NodeConfiguration nodeConfiguration = jsonReader.jsonToObject("/NodeConfiguration",
        NodeConfiguration.class);
    notaryIntegrationUtil.decrypt("", nodeConfiguration.getTransactionSecretKey());
  }

  private Boolean isAuthValid(String authToken) {
    return appConfig.getAuthToken().equals(authToken);
  }
}
