package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findEmployeeByName(String name);
}
