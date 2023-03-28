package com.accolitedigital.blockchain.node.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryLevelMiningAnswers {

  private String answer1;

  private String answer2;

  private String answer3;

  private String answer4;

  private String answer5;
}
