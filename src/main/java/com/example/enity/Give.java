package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Give {
  @JsonProperty(value = "gArticleId")
@ApiModelProperty("点赞的id")
  private long gArticleId;
  @JsonProperty(value = "gTouristId")
@ApiModelProperty("点赞的游客id")
  private String gTouristId;
  @JsonProperty(value = "gTime")
@ApiModelProperty("点赞的时间")
  private long gTime;



}
