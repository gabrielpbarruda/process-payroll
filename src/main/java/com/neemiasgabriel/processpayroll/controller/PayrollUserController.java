package com.neemiasgabriel.processpayroll.controller;

import com.neemiasgabriel.processpayroll.dto.PayrollUserDto;
import com.neemiasgabriel.processpayroll.service.PayrollUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/users")
public class PayrollUserController {

  private final PayrollUserService payrollUserService;

  @GetMapping
  public List<PayrollUserDto> getAll() {
    return payrollUserService.getAll();
  }

}
