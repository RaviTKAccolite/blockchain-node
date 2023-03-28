package com.accolitedigital.blockchain.node.service.impl;

import com.accolitedigital.blockchain.node.model.MessageInitializerRequestBody;
import com.accolitedigital.blockchain.node.model.NotaryTransactionResponse;
import com.accolitedigital.blockchain.node.service.NodeService;
import com.accolitedigital.blockchain.node.service.util.JsonReader;
import com.accolitedigital.blockchain.node.config.AppConfig;
import com.accolitedigital.blockchain.node.entity.NodeConfiguration;
import com.accolitedigital.blockchain.node.model.Nodes;
import com.accolitedigital.blockchain.node.model.TransactionValidationRequest;
import com.accolitedigital.blockchain.node.model.TransactionValidationResponse;
import com.accolitedigital.blockchain.node.service.util.NotaryIntegrationUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {

  private static final String NodeServiceImpl = "NodeServiceImpl";

  static Logger log = Logger.getLogger(NodeServiceImpl.class.getName());
  @Autowired
  JsonReader jsonReader;

  @Autowired
  NotaryIntegrationService notaryIntegrationService;

  @Autowired
  NotaryIntegrationUtil notaryIntegrationUtil;

  @Override
  public NotaryTransactionResponse messageInitializer(String authHeader,
      MessageInitializerRequestBody requestBody)
      throws Exception {
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerName()
        + "; message to be received by : " + requestBody.getAcceptorName()
        + "; with message : " + requestBody.getMessage());
    if (Boolean.TRUE.equals(isAuthValid(authHeader))) {
      //  Proceed calling notary.
      NotaryTransactionResponse notaryTransactionResponse = notaryIntegrationService.notaryInitTransaction(
          requestBody).getBody();
      return notaryTransactionResponse;
    } else {
      log.info(
          NodeServiceImpl + "Node with id: " + requestBody.getInitializerName() + " is not valid");
      throw new Exception("Id not valid");
    }
  }

  @Override
  public TransactionValidationResponse transactionValidation(
      TransactionValidationRequest requestBody) throws Exception {
    Nodes nodesList = jsonReader.jsonToObject("/NodesList.json", Nodes.class);

    if (!nodesList.getNodes().contains(requestBody.getInitializerId())) {
      log.info("error");
    }
    TransactionValidationResponse transactionValidationResponse = TransactionValidationResponse.builder()
        .isValidTransaction(Boolean.TRUE)
        .isDestination(Boolean.TRUE)
        .build();

    if (sendNodeConfiguration().getNodeId().equals(requestBody.getAcceptorId())) {
      // calling message acceptor for decryption
      messageAcceptor(requestBody);
    } else {
      transactionValidationResponse.setIsDestination(Boolean.FALSE);
    }
    return transactionValidationResponse;
  }

  public List<String> miningRequest() {
    JSONObject questionnaire = jsonReader.jsonToObject("/Questionnaire.json", JSONObject.class);

    return null;
  }

  private void messageAcceptor(TransactionValidationRequest requestBody) throws Exception {
    // TODO decrypting the data transferred
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerId()
        + "; message is received by : " + requestBody.getAcceptorId()
        + "; with message : " + requestBody.getMessage());
    String encryptedMessage = notaryIntegrationUtil.decrypt(requestBody.getMessage(),
        sendNodeConfiguration().getTransactionSecretKey());
    log.info("EncryptedMessage : " + encryptedMessage);
  }

  private Boolean isAuthValid(String authToken) {
    return sendNodeConfiguration().getAuthorizationToken().equals(authToken);
  }

  private NodeConfiguration sendNodeConfiguration() {
    return jsonReader.jsonToObject("/NodesConfiguration.json",
        NodeConfiguration.class);
  }
}
