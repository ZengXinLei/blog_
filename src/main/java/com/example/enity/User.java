package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User implements Cloneable {

  @JsonProperty(value = "uId")
@ApiModelProperty("����")
  private long uId;
  @JsonProperty(value = "uName")
@ApiModelProperty("�û���")
  private String uName;
  @JsonProperty(value = "uPassword")
@ApiModelProperty("����")
  private String uPassword;
  @JsonProperty(value = "uTime")
@ApiModelProperty("��¼ʱ��")
  private String uTime;
  @JsonProperty(value = "uHeadPortrait")
@ApiModelProperty("ͷ��")
  private String uHeadPortrait;
  @JsonProperty(value = "uTel")
@ApiModelProperty("�绰")
  private String uTel;
  @JsonProperty(value = "uLoginTime")
@ApiModelProperty("��¼ʱ��")
  private String uLoginTime;
  @JsonProperty(value = "uAttention")
@ApiModelProperty("������")
  private long uAttention;
  @JsonProperty(value = "uFans")
@ApiModelProperty("��˿")
  private long uFans;
  @JsonProperty(value = "uSex")
@ApiModelProperty("�Ա�")
  private String uSex;
  @JsonProperty(value = "uQq")
@ApiModelProperty("QQ")
  private String uQq;
  @JsonProperty(value = "uEmail")
@ApiModelProperty("�����ʼ�")
  private String uEmail;
  @JsonProperty(value = "uSchool")
@ApiModelProperty("ѧУ")
  private String uSchool;
  @JsonProperty(value = "uCompany")
@ApiModelProperty("��˾")
  private String uCompany;
  @JsonProperty(value = "uBirth")
@ApiModelProperty("����")
  private String uBirth;
  @JsonProperty(value = "uFaith")
@ApiModelProperty("����")
  private String uFaith;
  @JsonProperty(value = "uLevel")
@ApiModelProperty("Ȩ��")
  private int uLevel;




  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
