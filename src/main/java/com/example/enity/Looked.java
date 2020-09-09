package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Looked {

  @JsonProperty(value = "lArticleId" )
@ApiModelProperty("访问的游客id")
  private long lArticleId;
  @JsonProperty(value = "lTouristId" )
@ApiModelProperty("访问的文章id")
  private String lTouristId;
  @JsonProperty(value = "lTime" )
@ApiModelProperty("访问的时间")
  private long lTime;



}
