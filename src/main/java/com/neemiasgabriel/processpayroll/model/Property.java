package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property extends AbstractEntity {

  @Column(nullable = false, unique = true)
  private String name;

  private String value;

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof Property) {
      Property property = (Property) other;
      return this.name.equals(property.getName());
    }

    return false;
  }
}


