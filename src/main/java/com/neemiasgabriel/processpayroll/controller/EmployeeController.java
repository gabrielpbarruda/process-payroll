package com.neemiasgabriel.processpayroll.controller;

import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping("/registerEmployee")
  public void registerEmployee(@RequestBody Employee employee) {
    try {
      employeeService.register(employee);
    } catch (PatternNotMatcheException e) {
      e.printStackTrace();
    }
  }

  @GetMapping("/getBalance/{Id}")
  public Double getBalance(@PathVariable("id") Long enterpriseId) {
    return employeeService.getBalanceByEmplyeeId(enterpriseId);
  }
}
