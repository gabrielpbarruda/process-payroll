package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

  @Query(
    "SELECT new com.neemiasgabriel.processpayroll.dto" +
      ".EnterpriseDto(e.id, e.name, e.fantasyName, e.email, e.cnpj, e.accountBalance, e.ownerId) " +
    "FROM Enterprise e")
  List<EnterpriseDto> findAllByProjectedDto();

  boolean existsByCnpj(String cnpj);

  Optional<Enterprise> findByCnpj(String cnpj);

}
