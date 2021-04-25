package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {

  @Column(unique = true, nullable = false)
  private String accountNumber;

  @Column(nullable = false)
  private String agency;

  @Column(columnDefinition = "real default 0.0")
  private Double accountBalance;

  @OneToOne(mappedBy = "account")
  private PayrollUser payrollUser;

  public Account(String accountNumber, String agency) {
    this.accountNumber = accountNumber;
    this.agency = agency;
  }

  public Account(String accountNumber, String agency, Double accountBalance) {
    this.accountNumber = accountNumber;
    this.agency = agency;
    this.accountBalance = accountBalance;
  }
}
