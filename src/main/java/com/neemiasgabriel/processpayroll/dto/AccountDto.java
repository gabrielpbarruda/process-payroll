package com.neemiasgabriel.processpayroll.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@RequiredArgsConstructor
public class AccountDto {
  private final String accountNumber;

  private final String agency;

  private final Double accountBalance;

}
