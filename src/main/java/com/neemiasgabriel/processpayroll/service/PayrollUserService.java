package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.PayrollUserDto;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;

import java.util.List;

public interface PayrollUserService {

  List<PayrollUserDto> getAll();

  PayrollUserDto getByUsername(String username) throws DataNotFoundException;

  PayrollUserDto getByCpf(String cpf) throws DataNotFoundException;
}
