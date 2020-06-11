package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User implements Cloneable {

  @JsonProperty(value = "uId")
  private long uId;
  @JsonProperty(value = "uName")
  private String uName;
  @JsonProperty(value = "uPassword")
  private String uPassword;
  @JsonProperty(value = "uTime")
  private String uTime;
  @JsonProperty(value = "uHeadPortrait")
  private String uHeadPortrait;
  @JsonProperty(value = "uTel")
  private String uTel;
  @JsonProperty(value = "uLoginTime")
  private String uLoginTime;
  @JsonProperty(value = "uAttention")
  private long uAttention;
  @JsonProperty(value = "uFans")
  private long uFans;
  @JsonProperty(value = "uSex")
  private String uSex;
  @JsonProperty(value = "uQq")
  private String uQq;
  @JsonProperty(value = "uEmail")
  private String uEmail;
  @JsonProperty(value = "uSchool")
  private String uSchool;
  @JsonProperty(value = "uCompany")
  private String uCompany;
  @JsonProperty(value = "uBirth")
  private String uBirth;
  @JsonProperty(value = "uFaith")

  private String uFaith;
  @JsonProperty(value = "uLevel")

  private int uLevel;




  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
