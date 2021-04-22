package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dtos.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Enterprise;

import java.util.List;

public interface EnterpriseService {

  EnterpriseDto getById(Long id);
  void register(EnterpriseDto enterprise) throws PatternNotMatcheException;
  Double getBalanceById(Long enterpriseId);
  List<EnterpriseDto> getAllEnterprises();
  void processPayroll(Long enterpriseId) throws DataNotFoundException;
}
