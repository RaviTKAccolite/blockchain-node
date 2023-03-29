package com.accolitedigital.blockchain.node.service;

import com.accolitedigital.blockchain.node.model.MessageInitializerRequestBody;
import com.accolitedigital.blockchain.node.model.MiningRequestAnswers;
import com.accolitedigital.blockchain.node.model.NotaryTransactionResponse;
import com.accolitedigital.blockchain.node.model.TransactionValidationRequest;
import com.accolitedigital.blockchain.node.model.TransactionValidationResponse;
import java.util.List;

public interface NodeService {

  NotaryTransactionResponse messageInitializer(String authHeader, MessageInitializerRequestBody requestBody)
      throws Exception;

  TransactionValidationResponse transactionValidation(TransactionValidationRequest requestBody, String publicKey) throws Exception;

  List<String> miningRequest(String authHeader, MiningRequestAnswers miningAnswers)
      throws Exception;

  Boolean miningValidation(MiningRequestAnswers miningAnswer);
}
