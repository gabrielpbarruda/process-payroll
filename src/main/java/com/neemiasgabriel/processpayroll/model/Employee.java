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
public class Employee extends AbstractEntity {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date birthday;

  @Column(nullable = false, unique = true)
  private String email;

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name = "EMPLOYEE_ID")
  private List<Account> account;

  @Column(columnDefinition = "real default 0.0")
  private Double wage;

  @ManyToOne
  private Enterprise enterprise;

  public Employee(String name, String cpf, Date birthday, String email) {
    this.name = name;
    this.cpf = cpf;
    this.birthday = birthday;
    this.email = email;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cpf, email);
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
