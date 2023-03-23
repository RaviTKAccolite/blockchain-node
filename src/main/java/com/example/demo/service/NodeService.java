package com.example.demo.service;

import com.example.demo.model.MessageInitializerRequestBody;
import java.util.List;

public interface NodeService {

  void messageInitializer(MessageInitializerRequestBody requestBody);

  void transactionValidation(MessageInitializerRequestBody requestBody);

  List<String> miningRequest();
}
