package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enterprise extends AbstractEntity {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String fantasyName;

  @Column(nullable = false, unique = true)
  private String cnpj;

  @Column(nullable = false)
  private String email;

  @Column(columnDefinition = "real default 0.0")
  private Double accountBalance;

  @OneToMany
  private Set<Employee> employees = new HashSet<>();

  @Column(name = "payroll_user_id", insertable = false, updatable = false)
  private Long ownerId;

  public Enterprise(String name, String fantasyName, String cnpj, String email) {
    this.name = name;
    this.fantasyName = fantasyName;
    this.cnpj = cnpj;
    this.email = email;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cnpj);
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof Enterprise) {
      Enterprise enterprise = (Enterprise) other;
      return this.getCnpj().equals(enterprise);
    }

    return false;
  }
}
