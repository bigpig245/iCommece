package com.example.product.dto;

import com.example.product.dto.enumeration.Action;

import java.time.LocalDateTime;

public class UserActivityDto {
  private Long id;
  private Action action;
  private String url;
  private String query;
  private LocalDateTime trackedDate;
  private Long userId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public LocalDateTime getTrackedDate() {
    return trackedDate;
  }

  public void setTrackedDate(LocalDateTime trackedDate) {
    this.trackedDate = trackedDate;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
