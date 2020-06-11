package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User implements Cloneable {

  @JsonProperty(value = "uId")
@ApiModelProperty("主键")
  private long uId;
  @JsonProperty(value = "uName")
@ApiModelProperty("用户名")
  private String uName;
  @JsonProperty(value = "uPassword")
@ApiModelProperty("密码")
  private String uPassword;
  @JsonProperty(value = "uTime")
@ApiModelProperty("登录时间")
  private String uTime;
  @JsonProperty(value = "uHeadPortrait")
@ApiModelProperty("头像")
  private String uHeadPortrait;
  @JsonProperty(value = "uTel")
@ApiModelProperty("电话")
  private String uTel;
  @JsonProperty(value = "uLoginTime")
@ApiModelProperty("登录时间")
  private String uLoginTime;
  @JsonProperty(value = "uAttention")
@ApiModelProperty("座右铭")
  private long uAttention;
  @JsonProperty(value = "uFans")
@ApiModelProperty("粉丝")
  private long uFans;
  @JsonProperty(value = "uSex")
@ApiModelProperty("性别")
  private String uSex;
  @JsonProperty(value = "uQq")
@ApiModelProperty("QQ")
  private String uQq;
  @JsonProperty(value = "uEmail")
@ApiModelProperty("电子邮件")
  private String uEmail;
  @JsonProperty(value = "uSchool")
@ApiModelProperty("学校")
  private String uSchool;
  @JsonProperty(value = "uCompany")
@ApiModelProperty("公司")
  private String uCompany;
  @JsonProperty(value = "uBirth")
@ApiModelProperty("生日")
  private String uBirth;
  @JsonProperty(value = "uFaith")
@ApiModelProperty("信仰")
  private String uFaith;
  @JsonProperty(value = "uLevel")
@ApiModelProperty("权限")
  private int uLevel;




  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
