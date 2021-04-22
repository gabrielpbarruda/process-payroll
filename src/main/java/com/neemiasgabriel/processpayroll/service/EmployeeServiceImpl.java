package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Override
  public void register(Employee employee) throws PatternNotMatcheException {
    if (validateEmpolyeeRegister(employee)) {
      employeeRepository.save(employee);
    } else {
      throw new PatternNotMatcheException("CPF pattern does not match with the requirements");
    }
  }

  private boolean validateEmpolyeeRegister(Employee e) {
    if (e != null) {
      Pattern pattern = Pattern.compile("^d{3}.d{3}.d{3}-d{2}$");
      Matcher matcher = pattern.matcher(e.getCpf());

      return matcher.matches() && !e.getName().isEmpty() && !e.getCpf().isEmpty() && !e.getEmail().isEmpty() && e.getBirthday() != null;
    }

    return false;
  }

  @Override
  public Double getBalanceByEmplyeeId(Long employeeId) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);

    return employee.isPresent()
      ? employee.get().getAccountBalance()
      : null;
  }
}
