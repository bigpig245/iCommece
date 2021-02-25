package com.example.user.domain;

import com.example.user.dto.enumeration.Action;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity", schema = "service_user")
public class UserActivity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_c_generator")
  @GenericGenerator(
      name = "sequence_c_generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "service_user.user_activity_id_seq"),
          @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
          @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
      }
  )
  private Long id;

  @Column(name = "action", nullable = false)
  @Enumerated(EnumType.STRING)
  private Action action;

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "query")
  private String query;

  @Column(name = "trackedDate", nullable = false)
  private LocalDateTime trackedDate;

  @Column(name = "user_id", nullable = false)
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
