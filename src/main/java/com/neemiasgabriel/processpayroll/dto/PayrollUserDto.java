package com.neemiasgabriel.processpayroll.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PayrollUserDto {
  private String name;

  private String email;

  private String cpf;

  private String username;

  private String password;

  private String role;

  private Set<EnterpriseDto> enterprise = Set.of();

  private AccountDto account;
}
