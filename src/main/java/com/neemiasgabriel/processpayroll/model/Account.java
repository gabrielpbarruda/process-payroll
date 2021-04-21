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
public class Account extends AbstractEntity {
  @Column(nullable = false)
  private String agency;

  @Column(nullable = false, unique = true)
  private String accountNumber;

  @Column(columnDefinition = "real default 0.0")
  private Double balance;

  @Column(columnDefinition = "bool default true")
  private Boolean personalAccount;

  @Override
  public int hashCode() {
    return Objects.hash(agency, accountNumber);
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof Account) {
      Account acc = (Account) other;
      return this.agency.equals(acc.getAgency()) && this.accountNumber.equals(acc.getAccountNumber());
    }

    return false;
  }
}
