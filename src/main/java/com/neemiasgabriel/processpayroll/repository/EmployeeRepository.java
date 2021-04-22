package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.dtos.EmployeeDto;
import com.neemiasgabriel.processpayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findEmployeeByName(String name);

  @Query(
    "SELECT com.neemiasgabriel.processpayroll.dtos.EmployeeDto(e.id, e.name, e.cpf, e.birthday, e.email, e.enterpriseId) " +
    "FROM Employee e " +
    "WHERE e.enterpriseId = :enterpriseId"
  )
  List<EmployeeDto> findallProjectedByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}
