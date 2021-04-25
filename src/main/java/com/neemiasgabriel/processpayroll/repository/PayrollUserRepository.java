package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.model.PayrollUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollUserRepository extends JpaRepository<PayrollUser, Long> {
  Optional<PayrollUser> findByUsername(String username);

  Optional<PayrollUser> findByCpf(String cpf);
}
