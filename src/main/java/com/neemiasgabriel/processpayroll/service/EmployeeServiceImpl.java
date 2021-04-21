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
    if (employee != null) {
      Pattern pattern = Pattern.compile("^d{3}.d{3}.d{3}-d{2}$");
      Matcher matcher = pattern.matcher(employee.getCpf());

      if (matcher.matches()) {
        employeeRepository.save(employee);
      } else {
        throw new PatternNotMatcheException("CPF pattern does not matche with the requirements");
      }
    }
  }

  @Override
  public Double getBalanceByEmplyeeId(Long employeeId) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);

    return employee.isPresent()
      ? employee.get().getAccount().get(0).getBalance()
      : null;
  }
}
