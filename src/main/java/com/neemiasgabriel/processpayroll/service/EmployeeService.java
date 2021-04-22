package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;

import java.util.Set;

public interface EmployeeService {
  void register(Employee employee) throws PatternNotMatcheException;

  Double getBalanceByEmplyeeId(Long employeeId);

  Set<EmployeeDto> getAllByEnterpriseId(Long enterpriseId);
}
