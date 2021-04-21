package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

}
