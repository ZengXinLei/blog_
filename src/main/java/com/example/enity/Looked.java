package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Looked {

  @JsonProperty(value = "lArticleId" )
@ApiModelProperty("���ʵ��ο�id")
  private long lArticleId;
  @JsonProperty(value = "lTouristId" )
@ApiModelProperty("���ʵ�����id")
  private String lTouristId;
  @JsonProperty(value = "lTime" )
@ApiModelProperty("���ʵ�ʱ��")
  private long lTime;



}
