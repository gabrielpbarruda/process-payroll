package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;

import java.util.List;

public interface EnterpriseService {

  EnterpriseDto getById(Long id);
  void register(EnterpriseDto enterprise) throws PatternNotMatcheException, DataAlreadyExistsException;
  Double getBalanceById(Long enterpriseId);
  List<EnterpriseDto> getAllEnterprises();
  void processPayroll(Long enterpriseId) throws DataNotFoundException;
}
