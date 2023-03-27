package com.accolitedigital.blockchain.node.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageInitializerRequestBody {

  @NonNull
  private String initializerName;

  @NonNull
  private String acceptorName;

  private String message;

}
