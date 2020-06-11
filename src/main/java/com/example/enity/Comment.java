package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Comment {

  @JsonProperty(value = "cId")
@ApiModelProperty("��������")
  private String cId;
  @JsonProperty(value = "cText")
@ApiModelProperty("��������")
  private String cText;
  @JsonProperty(value = "cTouristId")
@ApiModelProperty("���۵��ο�")
  private String cTouristId;
  @JsonProperty(value = "cArticleId")
@ApiModelProperty("���۵�����")
  private long cArticleId;
  @JsonProperty(value = "cReviewId")
@ApiModelProperty("���۵�ID")
  private String cReviewId;
  @JsonProperty(value = "cState")
@ApiModelProperty("���۵�״̬")
  private String cState;
  @JsonProperty(value = "cTime")
@ApiModelProperty("���۵�ʱ��")
  private long cTime;


  private Tourist tourist;
  private Comment comment;
  private Article article;

}
