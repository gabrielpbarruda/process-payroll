package com.neemiasgabriel.processpayroll.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Version
  private Long version;

  @Temporal(TemporalType.TIMESTAMP)
  private Calendar createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Calendar updatedAt;

  @PrePersist
  public void prePersist() {
    setCreatedAt(Calendar.getInstance());
    setUpdatedAt(Calendar.getInstance());
  }

  @PreUpdate
  public void preUpdate() {
    setUpdatedAt(Calendar.getInstance());
  }
}
