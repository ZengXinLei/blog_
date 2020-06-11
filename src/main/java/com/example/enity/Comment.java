package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Comment {

  @JsonProperty(value = "cId")

  private String cId;
  @JsonProperty(value = "cText")

  private String cText;
  @JsonProperty(value = "cTouristId")

  private String cTouristId;
  @JsonProperty(value = "cArticleId")

  private long cArticleId;
  @JsonProperty(value = "cReviewId")

  private String cReviewId;
  @JsonProperty(value = "cState")

  private String cState;
  @JsonProperty(value = "cTime")

  private long cTime;


  private Tourist tourist;
  private Comment comment;
  private Article article;

}
