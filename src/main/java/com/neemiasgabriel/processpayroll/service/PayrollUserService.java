package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.PayrollUserDto;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.model.PayrollUser;

import java.util.List;

public interface PayrollUserService {

  List<PayrollUserDto> getAll();

  PayrollUserDto getByUsername(String username) throws DataNotFoundException;

  PayrollUserDto getByCpf(String cpf) throws DataNotFoundException;

  PayrollUser getById(Long id);
}
