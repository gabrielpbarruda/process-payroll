package com.neemiasgabriel.processpayroll.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDto {
  private final Long id;

  private final String name;

  private final String cpf;

  private final Date birthday;

  private final String email;

  private final String referenceAccount;

  private final String referenceAgency;

  private final Double wage;

  private final Long enterpriseId;
}
