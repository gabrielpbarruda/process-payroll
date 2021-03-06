package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAccountNumberAndAgency(String accountNumber, String agency);
}
