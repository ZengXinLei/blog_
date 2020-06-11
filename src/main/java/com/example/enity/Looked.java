package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Looked {

  @JsonProperty(value = "lArticleId" )

  private long lArticleId;
  @JsonProperty(value = "lTouristId" )

  private String lTouristId;
  @JsonProperty(value = "lTime" )

  private long lTime;



}
