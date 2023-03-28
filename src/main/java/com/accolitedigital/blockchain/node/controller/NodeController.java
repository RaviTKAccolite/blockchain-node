package com.accolitedigital.blockchain.node.controller;

import com.accolitedigital.blockchain.node.model.MessageInitializerRequestBody;
import com.accolitedigital.blockchain.node.model.MiningRequestAnswers;
import com.accolitedigital.blockchain.node.model.TransactionValidationRequest;
import com.accolitedigital.blockchain.node.model.TransactionValidationResponse;
import com.accolitedigital.blockchain.node.service.NodeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class NodeController {

  @Autowired
  NodeService nodeService;

  private static final String NODE_CONTROLLER = "NodeController:: ";

  @PostMapping(value = "/messageInitializer")
  public void messageInitializer(
      @RequestHeader(name = "AuthHeader") String authHeader,
      @RequestBody MessageInitializerRequestBody requestBody) throws Exception {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "messageInitializer called by " + requestBody.getInitializerName());
    nodeService.messageInitializer(authHeader, requestBody);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
  }


  @PostMapping(value = "/transactionValidation")
  public TransactionValidationResponse transactionValidation(
      @RequestBody TransactionValidationRequest requestBody) throws Exception {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "transactionValidation called by " + "current node details ");
    TransactionValidationResponse transactionValidationResponse = nodeService.transactionValidation(requestBody);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
    return transactionValidationResponse;
  }

  @PostMapping(value = "/miningRequestQuestions")
  public List<String> miningRequest(
      @RequestHeader(value = "authHeader", required = false) String authHeader,
      @RequestBody(required = false) MiningRequestAnswers miningAnswers
  ) throws Exception {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "miningRequest called to " + "current node details ");
    List<String> questions = nodeService.miningRequest(authHeader, miningAnswers);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
    return questions;
  }

  @PostMapping(value = "/miningValidation")
  public Boolean miningValidation(@RequestBody MiningRequestAnswers miningRequestAnswers) {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "miningValidation called to " + "current node details ");
    Boolean status = nodeService.miningValidation(miningRequestAnswers);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
    return status;
  }

}
