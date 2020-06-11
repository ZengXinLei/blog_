package com.example.enity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Draft {

  private String did;

  private String dtitle;

  private String dtext;

  private String dhtml;

  private long dtime;

  private String dstate;

  private long duserId;

  private String dtype;


  private User user;


}
