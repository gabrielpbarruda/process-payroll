package com.neemiasgabriel.processpayroll;

import com.neemiasgabriel.processpayroll.model.Account;
import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import com.neemiasgabriel.processpayroll.model.Property;
import com.neemiasgabriel.processpayroll.repository.AccountRepository;
import com.neemiasgabriel.processpayroll.repository.EmployeeRepository;
import com.neemiasgabriel.processpayroll.repository.EnterpriseRepository;
import com.neemiasgabriel.processpayroll.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

  private final EnterpriseRepository enterpriseRepository;
  private final EmployeeRepository employeeRepository;
  private final AccountRepository accountRepository;
  private final PropertyRepository propertyRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    Property initializer = propertyRepository.findByName("db_initializer");

    if (initializer == null || initializer.getValue().equalsIgnoreCase("false")) {
      initAccounts();
      initEmployees();
      initEnterprises();

      if (initializer == null) {
        initializer = new Property();
        initializer.setName("db_initializer");
        propertyRepository.save(initializer);
      }

      initializer.setValue("true");
    }
  }

  private void initEnterprises() {
    enterpriseRepository.deleteAll();

    Enterprise edson = new Enterprise("Edson Arandes", "Side By Side", "12300000/0001-70");
    edson.setEmployees(Set.of(
      employeeRepository.findEmployeeByName("Taffarel").get(),
      employeeRepository.findEmployeeByName("Dunga").get()
    ));

    edson.setAccount(List.of(accountRepository.findAccountByAccountNumber("123654-1").get()));

    Enterprise john = new Enterprise("Joao das Neves", "Watch Patrol", "32100000/0001-71");
    john.getEmployees().add(employeeRepository.findEmployeeByName("Romario").get());
    john.setAccount(List.of(accountRepository.findAccountByAccountNumber("456789-1").get()));

    Enterprise mussum = new Enterprise("Mussum", "Afilcs", "32140000/0001-82");
    mussum.getEmployees().add(employeeRepository.findEmployeeByName("Rivaldo").get());
    mussum.setAccount(List.of(accountRepository.findAccountByAccountNumber("987654-1").get()));

    enterpriseRepository.saveAll(List.of(edson, john, mussum));
  }

  private void initEmployees() {
    employeeRepository.deleteAll();
    Calendar c = Calendar.getInstance();

    c.set(1966, Calendar.MAY,8);
    Employee taffarel = new Employee("Taffarel","123.456.789-01", c.getTime(),"taffarel@email.com");
    taffarel.setAccount(List.of(accountRepository.findAccountByAccountNumber("123456-1").get()));
    taffarel.setWage(3000.0);


    c.set(1963, Calendar.OCTOBER,31);
    Employee dunga = new Employee("Dunga", "321.654.987-02", c.getTime(), "dunga@email.com");
    dunga.setAccount(List.of(accountRepository.findAccountByAccountNumber("654321-1").get()));
    dunga.setWage(2500.0);

    c.set(1966, Calendar.JANUARY,29);
    Employee romario = new Employee("Romario", "213.564.879-03", c.getTime(), "romario@email.com");
    romario.setAccount(List.of(accountRepository.findAccountByAccountNumber("512345-1").get()));
    romario.setWage(1000.0);

    c.set(1972, Calendar.APRIL, 19);
    Employee rivaldo = new Employee("Rivaldo", "312.456.670-04", c.getTime(), "rivaldo@email.com");
    rivaldo.setAccount(List.of(accountRepository.findAccountByAccountNumber("654121-1").get()));
    rivaldo.setWage(2000.0);

    employeeRepository.saveAll(List.of(taffarel, dunga, romario, rivaldo));
  }

  private void initAccounts() {
    accountRepository.deleteAll();

    List<Account> accounts = List.of(
      new Account("0001", "123456-1", 100.0d, true),
      new Account("0001", "654321-1", 300.0d, true),
      new Account("0001", "512345-1", 0.0d, true),
      new Account("0001", "654121-1", 10.0d, true),
      new Account("0001", "123654-1", 100000.0d, false),
      new Account("0001", "456789-1", -1000.0d, false),
      new Account("0001", "987654-1", 20000.0d, false)
    );

    accountRepository.saveAll(accounts);
  }
}
