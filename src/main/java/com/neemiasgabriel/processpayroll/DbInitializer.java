package com.neemiasgabriel.processpayroll;

import com.neemiasgabriel.processpayroll.model.*;
import com.neemiasgabriel.processpayroll.repository.*;
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

  private final AccountRepository accountRepository;
  private final PayrollUserRepository payrollUserRepository;
  private final EnterpriseRepository enterpriseRepository;
  private final EmployeeRepository employeeRepository;
  private final PropertyRepository propertyRepository;

  @Override
  @Transactional(rollbackOn = Exception.class)
  public void run(String... args) throws Exception {
    Property initializer = propertyRepository.findByName("db_initializer");

    if (initializer == null || initializer.getValue().equalsIgnoreCase("false")) {
      initAccounts();
      initEmployees();
      initEnterprises();
      initUsers();

      if (initializer == null) {
        initializer = new Property();
        initializer.setName("db_initializer");
        propertyRepository.save(initializer);
      }

      initializer.setValue("true");
    }
  }

  private void initUsers() {
    Calendar c = Calendar.getInstance();

    c.set(1940, Calendar.OCTOBER, 23);
    PayrollUser pele = new PayrollUser("edsonarandes", "edson123", "ROLE_ENTERPRISE");
    pele.setName("Edson Arandes do Nascimento");
    pele.setEmail("edson@email.com");
    pele.setCpf("123.456.781-02");
    pele.setBirthday(c.getTime());
    pele.setAccount(accountRepository.findByAccountNumberAndAgency("123456-1", "000001").orElse(null));
    pele.setEnterprises(Set.of(enterpriseRepository.findByCnpj("12.300.000/0001-70").get()));

    c.set(2000, Calendar.JANUARY, 25);
    PayrollUser john = new PayrollUser("joaodasneves", "joao123", "ROLE_ENTERPRISE");
    john.setName("Joao das Neves");
    john.setEmail("joao@email.com");
    john.setCpf("123.457.781-02");
    john.setBirthday(c.getTime());
    john.setAccount(accountRepository.findByAccountNumberAndAgency("654321-1", "000001").orElse(null));
    john.setEnterprises(Set.of(enterpriseRepository.findByCnpj("32.100.000/0001-71").get()));

    c.set(1941, Calendar.APRIL, 7);
    PayrollUser mussum = new PayrollUser("mussum", "mussum123", "ROLE_ENTERPRISE");
    mussum.setName("Antônio Carlos Bernardes Gomes");
    mussum.setEmail("mussum@email.com");
    mussum.setCpf("223.457.781-02");
    mussum.setBirthday(c.getTime());
    mussum.setAccount(accountRepository.findByAccountNumberAndAgency("987654-1", "000001").orElse(null));
    mussum.setEnterprises(Set.of(enterpriseRepository.findByCnpj("32.140.000/0001-82").get()));

    c.set(1966, Calendar.MAY,8);
    PayrollUser taffarel = new PayrollUser("taffarel","taffarel123", "ROLE_USER");
    taffarel.setName("Taffarel");
    taffarel.setEmail("taffarel@email.com");
    taffarel.setCpf("123.456.789-01");
    taffarel.setBirthday(c.getTime());
    taffarel.setAccount(accountRepository.findByAccountNumberAndAgency("456789-1", "000001").orElse(null));

    c.set(1963, Calendar.OCTOBER,31);
    PayrollUser dunga = new PayrollUser("dunga", "dunga123", "ROLE_USER");
    dunga.setName("Dunga");
    dunga.setEmail("dunga@email.com");
    dunga.setCpf("321.654.987-02");
    dunga.setBirthday(c.getTime());
    dunga.setAccount(accountRepository.findByAccountNumberAndAgency("789456-1", "000001").orElse(null));

    c.set(1966, Calendar.JANUARY,29);
    PayrollUser romario = new PayrollUser("romario", "romario123", "ROLE_USER");
    romario.setName("Romario");
    romario.setEmail("romario@email.com");
    romario.setCpf("213.564.879-03");
    romario.setBirthday(c.getTime());
    romario.setAccount(accountRepository.findByAccountNumberAndAgency("456123-1", "000001").orElse(null));

    c.set(1972, Calendar.APRIL, 19);
    PayrollUser rivaldo = new PayrollUser("rivaldo", "rivaldo123", "ROLE_USER");
    rivaldo.setName("Rivaldo");
    rivaldo.setEmail("rivaldo@email.com");
    rivaldo.setCpf("312.456.670-04");
    rivaldo.setBirthday(c.getTime());
    rivaldo.setAccount(accountRepository.findByAccountNumberAndAgency("789123-1", "000001").orElse(null));

    payrollUserRepository.saveAll(List.of(pele, john, mussum, taffarel, dunga, romario, rivaldo));
  }

  private void initAccounts() {
    accountRepository.saveAll(List.of(
      new Account("123456-1", "000001", 100000d),
      new Account("654321-1", "000001", 10000d),
      new Account("987654-1", "000001", 200000d),
      new Account("456789-1", "000001", 100d),
      new Account("789456-1", "000001", 300d),
      new Account("456123-1", "000001", 0d),
      new Account("789123-1", "000001", 10d)
    ));
  }

  private void initEnterprises() {
    enterpriseRepository.deleteAll();

    Enterprise edson = new Enterprise("Edson Arandes", "Side By Side", "12.300.000/0001-70", "sydebyside@email.com");
    edson.setEmployees(Set.of(
      employeeRepository.findEmployeeByName("Taffarel").get(),
      employeeRepository.findEmployeeByName("Dunga").get()
    ));

    edson.setAccountBalance(100000.0);

    Enterprise john = new Enterprise("Joao das Neves", "Watch Patrol", "32.100.000/0001-71", "watch@email.com");
    john.getEmployees().add(employeeRepository.findEmployeeByName("Romario").get());
    john.setAccountBalance(-1000.0);

    Enterprise mussum = new Enterprise("Mussum", "Afilks", "32.140.000/0001-82", "afilks@email.com");
    mussum.getEmployees().add(employeeRepository.findEmployeeByName("Rivaldo").get());
    mussum.setAccountBalance(20000.0);

    enterpriseRepository.saveAll(List.of(edson, john, mussum));
  }

  private void initEmployees() {
    employeeRepository.deleteAll();
    Calendar c = Calendar.getInstance();

    c.set(1966, Calendar.MAY,8);
    Employee taffarel = new Employee("Taffarel","123.456.789-01", c.getTime(),"taffarel@email.com");
    taffarel.setReferenceAccount("456789-1");
    taffarel.setReferenceAgency("000001");
    taffarel.setWage(3000.0);

    c.set(1963, Calendar.OCTOBER,31);
    Employee dunga = new Employee("Dunga", "321.654.987-02", c.getTime(), "dunga@email.com");
    dunga.setReferenceAccount("789456-1");
    dunga.setReferenceAgency("000001");
    dunga.setWage(2500.0);

    c.set(1966, Calendar.JANUARY,29);
    Employee romario = new Employee("Romario", "213.564.879-03", c.getTime(), "romario@email.com");
    romario.setReferenceAccount("456123-1");
    romario.setReferenceAgency("000001");
    romario.setWage(1000.0);

    c.set(1972, Calendar.APRIL, 19);
    Employee rivaldo = new Employee("Rivaldo", "312.456.670-04", c.getTime(), "rivaldo@email.com");
    rivaldo.setReferenceAccount("789123-1");
    rivaldo.setReferenceAgency("000001");
    rivaldo.setWage(2000.0);

    employeeRepository.saveAll(List.of(taffarel, dunga, romario, rivaldo));
  }
}
