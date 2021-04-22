package com.neemiasgabriel.processpayroll;

import com.neemiasgabriel.processpayroll.model.Employee;
import com.neemiasgabriel.processpayroll.model.Enterprise;
import com.neemiasgabriel.processpayroll.model.Property;
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
  private final PropertyRepository propertyRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    Property initializer = propertyRepository.findByName("db_initializer");

    if (initializer == null || initializer.getValue().equalsIgnoreCase("false")) {
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

    Enterprise edson = new Enterprise("Edson Arandes", "Side By Side", "12300000/0001-70", "sydebyside@email.com");
    edson.setEmployees(Set.of(
      employeeRepository.findEmployeeByName("Taffarel").get(),
      employeeRepository.findEmployeeByName("Dunga").get()
    ));

    edson.setAccountBalance(100000.0);

    Enterprise john = new Enterprise("Joao das Neves", "Watch Patrol", "32100000/0001-71", "watch@email.com");
    john.getEmployees().add(employeeRepository.findEmployeeByName("Romario").get());
    john.setAccountBalance(-1000.0);

    Enterprise mussum = new Enterprise("Mussum", "Afilcs", "32140000/0001-82", "afilks@email.com");
    mussum.getEmployees().add(employeeRepository.findEmployeeByName("Rivaldo").get());
    mussum.setAccountBalance(20000.0);

    enterpriseRepository.saveAll(List.of(edson, john, mussum));
  }

  private void initEmployees() {
    employeeRepository.deleteAll();
    Calendar c = Calendar.getInstance();

    c.set(1966, Calendar.MAY,8);
    Employee taffarel = new Employee("Taffarel","123.456.789-01", c.getTime(),"taffarel@email.com");
    taffarel.setAccountBalance(100.0d);
    taffarel.setWage(3000.0);


    c.set(1963, Calendar.OCTOBER,31);
    Employee dunga = new Employee("Dunga", "321.654.987-02", c.getTime(), "dunga@email.com");
    dunga.setAccountBalance(300.0d);
    dunga.setWage(2500.0);

    c.set(1966, Calendar.JANUARY,29);
    Employee romario = new Employee("Romario", "213.564.879-03", c.getTime(), "romario@email.com");
    romario.setAccountBalance(0.0);
    romario.setWage(1000.0);

    c.set(1972, Calendar.APRIL, 19);
    Employee rivaldo = new Employee("Rivaldo", "312.456.670-04", c.getTime(), "rivaldo@email.com");
    rivaldo.setAccountBalance(10.0);
    rivaldo.setWage(2000.0);

    employeeRepository.saveAll(List.of(taffarel, dunga, romario, rivaldo));
  }
}
