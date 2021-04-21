package com.neemiasgabriel.processpayroll.controller;

import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import com.neemiasgabriel.processpayroll.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {

  private final EnterpriseService enterpriseService;

  @GetMapping("/")
  public List<Enterprise> getAllEnterprises() {
    return enterpriseService.getAllEnterprises();
  }

  @PutMapping("/processPayroll/{id}")
  public ResponseEntity<Object> processPayroll(@PathVariable("id") Long enterpriseId) {
    try {
      enterpriseService.processPayroll(enterpriseId);
      return new ResponseEntity<Object>("Payroll processed with success", HttpStatus.OK);
    } catch (DataNotFoundException e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerEnterprise(@RequestBody Enterprise enterprise) {
    try {
      enterpriseService.register(enterprise);
      return new ResponseEntity<Object>("Enterprise registered with success",HttpStatus.OK);
    } catch (PatternNotMatcheException e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getBalance/{id}")
  public Double getBalance(@PathVariable("id") Long enterpriseId) {
    return enterpriseService.getBalanceById(enterpriseId);
  }
}
