package com.neemiasgabriel.processpayroll.validators;

import com.neemiasgabriel.processpayroll.dto.EnterpriseDto;
import com.neemiasgabriel.processpayroll.exeception.MissingDataException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EnterpriseValidator {
  public boolean validateEnterpriseRegister(EnterpriseDto e) throws MissingDataException {
    if (e != null) {
      List<String> errors = List.of();

      Pattern pattern = Pattern.compile("^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$");
      Matcher matcher = pattern.matcher(e.getCnpj());

      if (!matcher.matches()) {
        errors.add("CNPJ pattern does not match with the requirements");
      }

      if (e.getName().isEmpty()) {
        errors.add("Enterprise name cannot be empty");
      }

      if (e.getEmail().isEmpty()) {
        errors.add("Email cannot be empty");
      }

      if (e.getCnpj().isEmpty()) {
        errors.add("CNPJ cannot be empty");
      }

      if (errors.size() > 0) {
        throw new MissingDataException(String.join(",", errors));
      }

      return true;
    }

    return false;
  }
}
