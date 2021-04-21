package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

  @OneToMany(fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  @JoinColumn(name = "ENTERPRISE_ID")
  private List<Account> account;

  @OneToMany
  private Set<Employee> employees = new HashSet<>();

  public Enterprise(String name, String fantasyName, String cnpj) {
    this.name = name;
    this.fantasyName = fantasyName;
    this.cnpj = cnpj;
    this.account = account;
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
