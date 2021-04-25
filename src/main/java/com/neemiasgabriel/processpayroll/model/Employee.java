package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends SimpleUser {

  @Column(columnDefinition = "real default 0.0")
  private Double wage;

  @Column(nullable = false)
  private String referenceAccount;

  @Column(nullable = false)
  private String referenceAgency;

  @Column(name = "enterprise_id", insertable = false, updatable = false)
  private Long enterpriseId;

  @ManyToOne
  private Enterprise enterprise;

  public Employee(String name, String cpf, Date birthday, String email) {
    setName(name);
    setCpf(cpf);
    setBirthday(birthday);
    setEmail(email);
  }

  public Employee(String name, String cpf, Date birthday, String email, Double wage) {
    setName(name);
    setCpf(cpf);
    setBirthday(birthday);
    setEmail(email);
    this.wage = wage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getCpf(), getEmail());
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof Employee) {
      Employee employee = (Employee) other;
      return this.getCpf().equals(employee);
    }

    return false;
  }
}
