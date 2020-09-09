package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Comment {

  @JsonProperty(value = "cId")
@ApiModelProperty("评论主键")
  private String cId;
  @JsonProperty(value = "cText")
@ApiModelProperty("评论内容")
  private String cText;
  @JsonProperty(value = "cTouristId")
@ApiModelProperty("评论的游客")
  private String cTouristId;
  @JsonProperty(value = "cArticleId")
@ApiModelProperty("评论的帖子")
  private long cArticleId;
  @JsonProperty(value = "cReviewId")
@ApiModelProperty("评论的ID")
  private String cReviewId;
  @JsonProperty(value = "cState")
@ApiModelProperty("评论的状态")
  private String cState;
  @JsonProperty(value = "cTime")
@ApiModelProperty("评论的时间")
  private long cTime;


  private Tourist tourist;
  private Comment comment;
  private Article article;

}
