package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findEmployeeByName(String name);

  @Query(
    "SELECT DISTINCT new com.neemiasgabriel.processpayroll.dto.EmployeeDto(" +
      "e.id, e.name, e.cpf, e.birthday, e.email, e.referenceAccount, e.referenceAgency, e.wage, e.enterpriseId) " +
    "FROM Employee e " +
    "WHERE e.enterpriseId = :enterpriseId"
  )
  Set<EmployeeDto> findallProjectedByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

  boolean existsByCpf(String cpf);

  boolean existsByEmail(String email);
}
