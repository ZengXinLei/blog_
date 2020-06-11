package com.example.enity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Draft {

  private String did;
@ApiModelProperty("草稿id")
  private String dtitle;
@ApiModelProperty("草稿的标题")
  private String dtext;
@ApiModelProperty("草稿的text内容")
  private String dhtml;
@ApiModelProperty("草稿HTML内容")
  private long dtime;
@ApiModelProperty("草稿的时间")
  private String dstate;
@ApiModelProperty("草稿的状态")
  private long duserId;
@ApiModelProperty("草稿的管理员id")
  private String dtype;
@ApiModelProperty("草稿的类型")

  private User user;


}
