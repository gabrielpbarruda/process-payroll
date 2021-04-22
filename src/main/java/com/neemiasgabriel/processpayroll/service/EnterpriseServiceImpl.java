package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.exeception.PatternNotMatcheException;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import com.neemiasgabriel.processpayroll.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

  private final EnterpriseRepository enterpriseRepository;

  @Override
  public EnterpriseDto getById(Long id) {
    Enterprise e = enterpriseRepository.findById(id).get();
    EnterpriseDto eDto = new EnterpriseDto(e.getId(), e.getName(), e.getFantasyName(), e.getEmail(), e.getCnpj(), e.getAccountBalance());
    eDto.setEmployees(e.getEmployees());

    return eDto;
  }

  @Override
  public void register(EnterpriseDto enterprise) throws PatternNotMatcheException {
    if (validateEnterpriseRegister(enterprise)) {
      enterpriseRepository.save(setFields(enterprise));
    } else {
      throw new PatternNotMatcheException("CNPJ pattern does not matche with the requirements");
    }
  }

  private boolean validateEnterpriseRegister(EnterpriseDto e) {
    if (e != null) {
      Pattern pattern = Pattern.compile("^d{2}.d{3}.d{3}/d{4}-d{2}$");
      Matcher matcher = pattern.matcher(e.getCnpj());

      return matcher.matches() && !e.getName().isEmpty() && !e.getCnpj().isEmpty() && !e.getEmail().isEmpty();
    }

    return false;
  }

  private Enterprise setFields(EnterpriseDto dto) {
    Enterprise enterprise = new Enterprise(dto.getName(), dto.getFantasyName(), dto.getCnpj(), dto.getEmail());
    enterprise.setAccountBalance(dto.getAccountBalance());

    return enterprise;
  }

  @Override
  public Double getBalanceById(Long enterpriseId) {
    Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

    return enterprise.isPresent()
      ? enterprise.get().getAccountBalance()
      : null;
  }

  @Override
  public List<EnterpriseDto> getAllEnterprises() {
    return enterpriseRepository.findAllByProjectedDto();
  }

  @Override
  @Transactional
  public void processPayroll(Long enterpriseId) throws DataNotFoundException {
    Optional<Enterprise> enterpriseOpt = enterpriseRepository.findById(enterpriseId);

    if (enterpriseOpt.isPresent()) {
      Enterprise enterprise = enterpriseOpt.get();
      Set<Employee> employees = enterprise.getEmployees();

      Double totalWage = employees.stream().map(emp -> emp.getWage()).reduce(0.0, Double::sum);
      Double reducedBalance = totalWage + (totalWage * 0.038);
      Double balance = enterprise.getAccountBalance();

      enterprise.setAccountBalance(balance - reducedBalance);

      employees.forEach(emp -> {
        Double actualBalance = emp.getAccountBalance();
        emp.setAccountBalance(actualBalance + emp.getWage());
      });
    } else {
      throw new DataNotFoundException("It was not possible to find the enterprise. The payroll will not be processed");
    }
  }
}
