package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class Article {
  @JsonProperty(value = "aId")
@ApiModelProperty("��������")
  private long aId;
  @JsonProperty(value = "aTitle")
@ApiModelProperty("���±���")
  private String aTitle;
  @JsonProperty(value = "aText")
@ApiModelProperty("���´�text")
  private String aText;
  @JsonProperty(value = "aHtml")
@ApiModelProperty("����HTML����")
  private String aHtml;
  @JsonProperty(value = "aUserId")
@ApiModelProperty("��������")
  private long aUserId;
  @JsonProperty(value = "aTime")
@ApiModelProperty("���µķ���ʱ��")
  private long aTime;
  @JsonProperty(value = "aState")
@ApiModelProperty("����״̬")
  private String aState;
  @JsonProperty(value = "aClickCount")
@ApiModelProperty("��������")
  private long aClickCount;
  @JsonProperty(value = "aReviewCount")
@ApiModelProperty("��������")
  private long aReviewCount;
  @JsonProperty(value = "aReadCount")
@ApiModelProperty("�Ķ�����")
  private long aReadCount;
  @JsonProperty(value = "aType")
@ApiModelProperty("��������")
  private String aType;

  private User user;



}
