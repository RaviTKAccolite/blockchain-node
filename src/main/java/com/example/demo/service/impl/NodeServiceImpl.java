package com.example.demo.service.impl;

import com.example.demo.model.MessageInitializerRequestBody;
import com.example.demo.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService {

  private static final String NodeServiceImpl = "NodeServiceImpl";

  @Override
  public void messageInitializer(MessageInitializerRequestBody requestBody) {
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerId()
      + "; message to be received by : " + requestBody.getAcceptorId()
      + "; with message : " + requestBody.getMessage());

  }

  @Override
  public void transactionValidation(MessageInitializerRequestBody requestBody) {
    if(requestBody.getAcceptorId().equals("current node id")){
      //TODO
      messageAcceptor(requestBody);
    }
  }

  private void messageAcceptor(MessageInitializerRequestBody requestBody){
    // TODO decrypting the data transferred
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerId()
        + "; message is received by : " + requestBody.getAcceptorId()
        + "; with message : " + requestBody.getMessage());
  }
}
