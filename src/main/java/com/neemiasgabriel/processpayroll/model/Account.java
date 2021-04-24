package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

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

  @Column(name = "payroll_user_id", insertable = false, updatable = false)
  private Long ownerId;

  public Account(String accountNumber, String agency) {
    this.accountNumber = accountNumber;
    this.agency = agency;
  }
}
