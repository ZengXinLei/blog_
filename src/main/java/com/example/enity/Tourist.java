package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tourist {

  @JsonProperty(value = "tId")
  private String tId;
  @JsonProperty(value = "tIp")
  private String tIp;
  @JsonProperty(value = "tTime")
  private long tTime;

  @JsonProperty(value = "tPhoto")
  private String tPhoto;
  @JsonProperty(value = "tName")
  private String tName;



}
