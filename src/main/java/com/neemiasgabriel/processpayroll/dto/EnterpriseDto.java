package com.neemiasgabriel.processpayroll.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class EnterpriseDto {
  private final Long id;

  private final String name;

  private final String fantasyName;

  private final String email;

  private final String cnpj;

  private final Double accountBalance;

  private Set<EmployeeDto> employees = Set.of();
}
