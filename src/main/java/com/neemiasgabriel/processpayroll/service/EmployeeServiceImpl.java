package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.exeception.DataAlreadyExistsException;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.MissingDataException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import com.neemiasgabriel.processpayroll.repository.EmployeeRepository;
import com.neemiasgabriel.processpayroll.repository.EnterpriseRepository;
import com.neemiasgabriel.processpayroll.validators.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final PayrollUserService payrollUserService;
  private final EnterpriseRepository enterpriseRepository;
  private final EmployeeValidator employeeValidator;

  @Override
  @Transactional
  public void register(EmployeeDto emp) throws PatternNotMatcheException, DataAlreadyExistsException, DataNotFoundException, MissingDataException {
    if (employeeValidator.validateEmployeeRegister(emp)) {
      if (employeeRepository.existsByCpf(emp.getCpf())) {
        throw new DataAlreadyExistsException("CPF already exists");
      }

      if (employeeRepository.existsByEmail(emp.getEmail())) {
        throw new DataAlreadyExistsException("Email already exists");
      }

      Employee employee = employeeRepository.save(new Employee(emp.getName(), emp.getCpf(), emp.getBirthday(), emp.getEmail(), emp.getWage()));
      Enterprise ent = enterpriseRepository.findById(emp.getEnterpriseId())
        .orElseThrow(() -> new DataNotFoundException("Enterprise not found"));

      ent.getEmployees().add(employee);
    } else {
      throw new PatternNotMatcheException("CPF pattern does not match with the requirements");
    }
  }

  @Override
  public Double getBalanceByEmployeeId(Long employeeId) throws DataNotFoundException {
    Optional<Employee> employee = employeeRepository.findById(employeeId);

    return employee.isPresent()
      ? payrollUserService.getByCpf(employee.get().getCpf()).getAccount().getAccountBalance()
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
        e.getReferenceAccount(),
        e.getReferenceAgency(),
        e.getWage(),
        e.getEnterpriseId()))
      .collect(Collectors.toList());
  }
}
