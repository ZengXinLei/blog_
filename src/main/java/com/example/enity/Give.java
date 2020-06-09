package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Give {
  @JsonProperty(value = "gArticleId")
  private long gArticleId;
  @JsonProperty(value = "gTouristId")
  private String gTouristId;
  @JsonProperty(value = "gTime")
  private long gTime;



}
