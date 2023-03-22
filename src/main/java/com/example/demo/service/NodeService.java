package com.example.demo.service;

import com.example.demo.model.MessageInitializerRequestBody;
public interface NodeService {

  void messageInitializer(MessageInitializerRequestBody requestBody);

  void transactionValidation(MessageInitializerRequestBody requestBody);
}
