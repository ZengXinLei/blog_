package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Give {
  @JsonProperty(value = "gArticleId")
@ApiModelProperty("���޵�id")
  private long gArticleId;
  @JsonProperty(value = "gTouristId")
@ApiModelProperty("���޵��ο�id")
  private String gTouristId;
  @JsonProperty(value = "gTime")
@ApiModelProperty("���޵�ʱ��")
  private long gTime;



}
