package com.neemiasgabriel.processpayroll.controller;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.MissingDataException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping("/register")
  public ResponseEntity<Object> registerEmployee(@RequestBody EmployeeDto employee) {
    try {
      employeeService.register(employee);
      return new ResponseEntity<Object>("Employee registered with success", HttpStatus.OK);
    } catch (PatternNotMatcheException | DataAlreadyExistsException | DataNotFoundException | MissingDataException e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getBalance/{id}")
  public ResponseEntity<Object> getBalance(@PathVariable("id") Long id) {
    try {
      return new ResponseEntity<Object>(employeeService.getBalanceByEmployeeId(id), HttpStatus.OK);
    } catch (DataNotFoundException e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public List<EmployeeDto> getAllEmployees() {
    return employeeService.getAll();
  }
}
