package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class Article {
  @JsonProperty(value = "aId")
@ApiModelProperty("文章主键")
  private long aId;
  @JsonProperty(value = "aTitle")
@ApiModelProperty("文章标题")
  private String aTitle;
  @JsonProperty(value = "aText")
@ApiModelProperty("文章纯text")
  private String aText;
  @JsonProperty(value = "aHtml")
@ApiModelProperty("文章HTML内容")
  private String aHtml;
  @JsonProperty(value = "aUserId")
@ApiModelProperty("文章作者")
  private long aUserId;
  @JsonProperty(value = "aTime")
@ApiModelProperty("文章的发布时间")
  private long aTime;
  @JsonProperty(value = "aState")
@ApiModelProperty("文章状态")
  private String aState;
  @JsonProperty(value = "aClickCount")
@ApiModelProperty("点赞数量")
  private long aClickCount;
  @JsonProperty(value = "aReviewCount")
@ApiModelProperty("评论数量")
  private long aReviewCount;
  @JsonProperty(value = "aReadCount")
@ApiModelProperty("阅读数量")
  private long aReadCount;
  @JsonProperty(value = "aType")
@ApiModelProperty("文章类型")
  private String aType;

  private User user;



}
