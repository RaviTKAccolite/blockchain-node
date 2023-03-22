package com.example.demo.controller;

import com.example.demo.model.MessageInitializerRequestBody;
import com.example.demo.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
      @RequestBody MessageInitializerRequestBody requestBody)
  {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "messageInitializer called by " + requestBody.getInitializerId());
    nodeService.messageInitializer(requestBody);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
  }


  @PostMapping(value = "/transactionValidation")
  public void transactionValidation(
      @RequestBody MessageInitializerRequestBody requestBody)
  {
    long startTime = System.nanoTime();
    log.info(NODE_CONTROLLER + "transactionValidation called by " + "current node details ");// TODO
    nodeService.transactionValidation(requestBody);
    long responseTime = System.nanoTime() - startTime;
    log.info(NODE_CONTROLLER + "response time in ns : "+ responseTime);
  }

}
