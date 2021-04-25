package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
  void register(EmployeeDto employee) throws PatternNotMatcheException, DataAlreadyExistsException;

  Double getBalanceByEmployeeId(Long employeeId) throws DataNotFoundException;

  Set<EmployeeDto> getAllByEnterpriseId(Long enterpriseId);

  List<EmployeeDto> getAll();
}
