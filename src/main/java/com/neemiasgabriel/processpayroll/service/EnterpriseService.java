package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Enterprise;

import java.util.List;

public interface EnterpriseService {

  void register(Enterprise enterprise) throws PatternNotMatcheException;
  Double getBalanceById(Long enterpriseId);
  List<Enterprise> getAllEnterprises();
  void processPayroll(Long enterpriseId) throws DataNotFoundException;
}
