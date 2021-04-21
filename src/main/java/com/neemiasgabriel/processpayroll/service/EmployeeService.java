package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;

public interface EmployeeService {
  void register(Employee employee) throws PatternNotMatcheException;

  Double getBalanceByEmplyeeId(Long employeeId);
}
