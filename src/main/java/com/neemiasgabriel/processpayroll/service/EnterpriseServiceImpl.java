package com.neemiasgabriel.processpayroll.service;

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

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

  private final EnterpriseRepository enterpriseRepository;

  @Override
  public void register(Enterprise enterprise) throws PatternNotMatcheException {
    if (enterprise != null) {
      Pattern pattern = Pattern.compile("^d{2}.d{3}.d{3}/d{4}-d{2}$");
      Matcher matcher = pattern.matcher(enterprise.getCnpj());

      if (matcher.matches()) {
        enterpriseRepository.save(enterprise);
      } else {
        throw new PatternNotMatcheException("CNPJ pattern does not matche with the requirements");
      }
    }
  }

  @Override
  public Double getBalanceById(Long enterpriseId) {
    Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);

    return enterprise.isPresent()
      ? enterprise.get().getAccount().get(0).getBalance()
      : null;
  }

  @Override
  public List<Enterprise> getAllEnterprises() {
    return enterpriseRepository.findAll();
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
      Double balance = enterprise.getAccount().get(0).getBalance();

      enterprise.getAccount().get(0).setBalance(balance - reducedBalance);

      employees.forEach(emp -> {
        Double actualBalance = emp.getAccount().get(0).getBalance();
        emp.getAccount().get(0).setBalance(actualBalance + emp.getWage());
      });
    } else {
      throw new DataNotFoundException("It was not possible to find the enterprise. The payroll will not be processed");
    }
  }
}
