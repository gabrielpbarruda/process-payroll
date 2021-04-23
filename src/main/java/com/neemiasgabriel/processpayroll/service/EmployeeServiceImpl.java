package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Override
  public void register(EmployeeDto emp) throws PatternNotMatcheException, DataAlreadyExistsException {
    if (validateEmpolyeeRegister(emp)) {
      if (employeeRepository.existsByCpf(emp.getCpf())) {
        throw new DataAlreadyExistsException("CPF already exists");
      }

      if (employeeRepository.existsByEmail(emp.getEmail())) {
        throw new DataAlreadyExistsException("Email already exists");
      }

      employeeRepository.save(new Employee(emp.getName(), emp.getCpf(), emp.getBirthday(), emp.getEmail(), emp.getWage()));
    } else {
      throw new PatternNotMatcheException("CPF pattern does not match with the requirements");
    }
  }

  private boolean validateEmpolyeeRegister(EmployeeDto e) {
    if (e != null) {
      Pattern pattern = Pattern.compile("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$");
      Matcher matcher = pattern.matcher(e.getCpf());

      return matcher.matches() &&
        !e.getName().isEmpty() &&
        !e.getCpf().isEmpty() &&
        !e.getEmail().isEmpty() &&
        e.getBirthday() != null &&
        e.getWage() >= 0d;
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

  @Override
  public Set<EmployeeDto> getAllByEnterpriseId(Long enterpriseId) {
    return employeeRepository.findallProjectedByEnterpriseId(enterpriseId);
  }

  @Override
  public List<EmployeeDto> getAll() {
    return employeeRepository.findAll().stream()
      .map(e -> new EmployeeDto(
        e.getId(),
        e.getName(),
        e.getCpf(),
        e.getBirthday(),
        e.getEmail(),
        e.getAccountBalance(),
        e.getWage(),
        e.getEnterpriseId()))
      .collect(Collectors.toList());
  }
}
