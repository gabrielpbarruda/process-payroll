package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.dto.AccountDto;
import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.dto.PayrollUserDto;
import com.neemiasgabriel.processpayroll.exeception.DataNotFoundException;
import com.neemiasgabriel.processpayroll.model.Account;
import com.neemiasgabriel.processpayroll.model.PayrollUser;
import com.neemiasgabriel.processpayroll.repository.PayrollUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollUserServiceImpl implements PayrollUserService {

  private final PayrollUserRepository repository;
  private final EnterpriseService enterpriseService;

  @Override
  public List<PayrollUserDto> getAll() {
    return repository.findAll().stream()
      .map(pu -> convertUser(pu)).collect(Collectors.toList());
  }

  @Override
  public PayrollUserDto getByUsername(String username) throws DataNotFoundException {
    PayrollUser user = repository.findByUsername(username)
      .orElseThrow(() -> new DataNotFoundException("User not found"));

    return convertUser(user);
  }

  @Override
  public PayrollUserDto getByCpf(String cpf) throws DataNotFoundException {
    PayrollUser user = repository.findByCpf(cpf)
      .orElseThrow(() -> new DataNotFoundException("User not found"));

    return convertUser(user);
  }

  private PayrollUserDto convertUser(PayrollUser pu) {
    PayrollUserDto puDto = new PayrollUserDto();
    Account acc = pu.getAccount();
    AccountDto accDto = new AccountDto(acc.getAccountNumber(), acc.getAgency(), acc.getAccountBalance());

    puDto.setName(pu.getName());
    puDto.setEmail(pu.getEmail());
    puDto.setCpf(pu.getCpf());
    puDto.setUsername(pu.getUsername());

    puDto.setAccount(accDto);

    if (!pu.getEnterprises().isEmpty()) {
      Set<EnterpriseDto> enterprises = pu.getEnterprises().stream()
        .map(ent -> {
          EnterpriseDto entDto = new EnterpriseDto(ent.getId(),
            ent.getName(),
            ent.getFantasyName(),
            ent.getEmail(),
            ent.getCnpj(),
            ent.getAccountBalance());

          entDto.setEmployees(enterpriseService.convertEmployees(ent));

          return entDto;
        }).collect(Collectors.toSet());

      puDto.setEnterprise(enterprises);
    }

    return puDto;
  }
}
