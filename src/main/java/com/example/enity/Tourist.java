package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Tourist {

  @JsonProperty(value = "tId")
@ApiModelProperty("�ο�����")
  private String tId;
  @JsonProperty(value = "tIp")
@ApiModelProperty("�ο�ip")
  private String tIp;
  @JsonProperty(value = "tTime")
@ApiModelProperty("�ο�ʱ��")
  private long tTime;

  @JsonProperty(value = "tPhoto")
@ApiModelProperty("�ο�ͷ��")
  private String tPhoto;
  @JsonProperty(value = "tName")
@ApiModelProperty("�ο�����")
  private String tName;



}
