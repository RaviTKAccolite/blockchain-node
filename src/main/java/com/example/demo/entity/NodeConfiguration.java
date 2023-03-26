package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeConfiguration {

  private String nodeName;

  private String nodeId;

  private String nodePassword;

  private String portNumber;

  private String transactionSecretKey;

  private List<String> authority;

  private String authorizationToken;

  private List<String> secretMiningKey;


}
