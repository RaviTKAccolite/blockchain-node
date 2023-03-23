package com.example.demo.service.impl;

import com.example.demo.model.MessageInitializerRequestBody;
import com.example.demo.service.NodeService;
import com.example.demo.service.util.JsonReader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NodeServiceImpl implements NodeService {

  private static final String NodeServiceImpl = "NodeServiceImpl";

  @Autowired
  JsonReader jsonReader;

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

  @Override
  public List<String> miningRequest() {
    JSONObject questionnaire =  jsonReader.jsonToObject("/Questionnaire.json", JSONObject.class);

    return null;
  }

  private void messageAcceptor(MessageInitializerRequestBody requestBody){
    // TODO decrypting the data transferred
    log.info(NodeServiceImpl + "message initialized by : " + requestBody.getInitializerId()
        + "; message is received by : " + requestBody.getAcceptorId()
        + "; with message : " + requestBody.getMessage());
  }
}
