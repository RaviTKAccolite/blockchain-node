package com.accolitedigital.blockchain.node.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionValidationResponse {

  private Boolean isDestination;

  private Boolean isValidTransaction;
}
