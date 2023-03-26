package com.example.demo.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Nodes {
  List<String> nodes;
}
