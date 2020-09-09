package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Log {
  @JsonProperty(value = "lTime")
  private long lTime;
  @JsonProperty(value = "lLevel")
  private String lLevel;
  @JsonProperty(value = "lClass")
  private String lClass;
  @JsonProperty(value = "lAction")
  private String lAction;
  @JsonProperty(value = "lMethod")
  private String lMethod;
  @JsonProperty(value = "lType")
  private String lType;

}
