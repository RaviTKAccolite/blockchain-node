package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaryTransactionRequest {
  @NonNull
  private String initializerName;

  @NonNull
  private String acceptorName;

  private String message;

  private Boolean isDestination;

  private Boolean isValidTransaction;
}
