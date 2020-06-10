package com.example.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class Article {
  @JsonProperty(value = "aId")
  private long aId;
  @JsonProperty(value = "aTitle")
  private String aTitle;
  @JsonProperty(value = "aText")
  private String aText;
  @JsonProperty(value = "aHtml")
  private String aHtml;
  @JsonProperty(value = "aUserId")
  private long aUserId;
  @JsonProperty(value = "aTime")
  private long aTime;
  @JsonProperty(value = "aState")
  private String aState;
  @JsonProperty(value = "aClickCount")
  private long aClickCount;
  @JsonProperty(value = "aReviewCount")
  private long aReviewCount;
  @JsonProperty(value = "aReadCount")
  private long aReadCount;
  @JsonProperty(value = "aType")
  private String aType;

  private User user;



}
