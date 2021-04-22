package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.dtos.EnterpriseDto;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

  @Query(
    "SELECT com.neemiasgabriel.processpayroll.dtos.EnterpriseDto(e.id, e.name, e.fantasyName, e.cpnj, e.accountBalance) " +
    "FROM Enterprise e")
  List<EnterpriseDto> findAllByProjectedDto();

}
