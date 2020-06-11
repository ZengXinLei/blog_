package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Tourist {

  @JsonProperty(value = "tId")
@ApiModelProperty("游客主键")
  private String tId;
  @JsonProperty(value = "tIp")
@ApiModelProperty("游客ip")
  private String tIp;
  @JsonProperty(value = "tTime")
@ApiModelProperty("游客时间")
  private long tTime;

  @JsonProperty(value = "tPhoto")
@ApiModelProperty("游客头像")
  private String tPhoto;
  @JsonProperty(value = "tName")
@ApiModelProperty("游客名字")
  private String tName;



}
