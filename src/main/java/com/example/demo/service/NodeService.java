package com.example.demo.service;

import com.example.demo.model.MessageInitializerRequestBody;
import java.util.List;

public interface NodeService {

  void messageInitializer(String authHeader, MessageInitializerRequestBody requestBody)
      throws Exception;

  void transactionValidation(MessageInitializerRequestBody requestBody) throws Exception;

  List<String> miningRequest();
}
