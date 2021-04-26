package com.neemiasgabriel.processpayroll.validators;

import com.neemiasgabriel.processpayroll.dto.EmployeeDto;
import com.neemiasgabriel.processpayroll.exeception.MissingDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeValidator {
  public boolean validateEmployeeRegister(EmployeeDto e) throws MissingDataException {
    List<String> errors = new ArrayList<>();

    if (e != null) {
      Pattern pattern = Pattern.compile("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$");
      Matcher matcher = pattern.matcher(e.getCpf());

      if (!matcher.matches()) {
        errors.add("CPF pattern does not match with the requirements");
      }

      if (e.getName().isEmpty()) {
        errors.add("Employee name cannot be empty");
      }

      if (e.getEmail().isEmpty()) {
        errors.add("Email cannot be empty");
      }

      if (e.getBirthday() == null) {
        errors.add("Birthday cannot be null");
      }

      if (e.getWage() < 0) {
        errors.add("Wage cannot be less than zero");
      }

      if (errors.size() > 0) {
        throw new MissingDataException(String.join("\n", errors));
      }

      return true;
    }

    return false;
  }
}
