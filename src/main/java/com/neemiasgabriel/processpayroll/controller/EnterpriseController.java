package com.neemiasgabriel.processpayroll.controller;

import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.MissingDataException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprises")
@RequiredArgsConstructor
public class EnterpriseController {

  private final EnterpriseService enterpriseService;

  @GetMapping
  public List<EnterpriseDto> getAllEnterprises() {
    return enterpriseService.getAllEnterprises();
  }

  @PutMapping("/processPayroll/{id}")
  public ResponseEntity<Object> processPayroll(@PathVariable("id") Long enterpriseId) {
    try {
      enterpriseService.processPayroll(enterpriseId);
      return new ResponseEntity<>("Payroll processed with success", HttpStatus.OK);
    } catch (DataNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerEnterprise(@RequestBody EnterpriseDto enterprise) {
    try {
      enterpriseService.register(enterprise);
      return new ResponseEntity<>("Enterprise registered with success", HttpStatus.OK);
    } catch (PatternNotMatcheException | DataAlreadyExistsException | DataNotFoundException | MissingDataException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getBalance/{id}")
  public ResponseEntity<Object> getBalance(@PathVariable("id") Long enterpriseId) {
    try {
      return new ResponseEntity<>(enterpriseService.getBalanceById(enterpriseId), HttpStatus.OK);
    } catch (DataNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getEnterprise/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") Long enterpriseId) {
    try {
      return new ResponseEntity<>(enterpriseService.getById(enterpriseId), HttpStatus.OK);
    } catch (DataNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
