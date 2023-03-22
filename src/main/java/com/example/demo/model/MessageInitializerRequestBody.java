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
public class MessageInitializerRequestBody {

  @NonNull
  private String initializerId;

  @NonNull
  private String acceptorId;

  private String message;

}
