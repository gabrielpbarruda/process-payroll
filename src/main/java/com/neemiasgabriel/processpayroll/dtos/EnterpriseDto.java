package com.neemiasgabriel.processpayroll.dtos;

import com.neemiasgabriel.processpayroll.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

  private Set<Employee> employees = new HashSet<>();
}
