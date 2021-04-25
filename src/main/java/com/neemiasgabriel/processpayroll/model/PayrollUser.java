package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PayrollUser extends SimpleUser {

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @OneToMany
  private Set<Enterprise> enterprises = Set.of();

  @OneToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(nullable = false)
  private String role;

  public PayrollUser(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public PayrollUser(String name, String email, String cpf, Date birthday, String username, String password) {
    setName(name);
    setEmail(email);
    setCpf(cpf);
    setBirthday(birthday);

    this.username = username;
    this.password = password;
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, getEmail(), getCpf());
  }

  @Override
  public boolean equals(Object other) {
    if (other != null && other instanceof PayrollUser) {
      PayrollUser user = (PayrollUser) other;

      return this.username.equals(user.getUsername())
        && getEmail().equals(user.getEmail())
        && getCpf().equals(user.getCpf());
    }

    return false;
  }
}
