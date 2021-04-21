package com.neemiasgabriel.processpayroll.repository;

import com.neemiasgabriel.processpayroll.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
  Property findByName(String name);
}
