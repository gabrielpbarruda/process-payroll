package com.neemiasgabriel.processpayroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

  @OneToOne
  private Enterprise enterprise;

  @Column(columnDefinition = "real default 0.0")
  private Double wage;

  @Column(nullable = false)
  private String role;

  public PayrollUser(String username, String email, String password, String cpf, Date birthday) {
    this.username = username;
    this.setEmail(email);
    this.password = password;
    setCpf(cpf);
    this.setBirthday(birthday);
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
        && this.cpf.equals(user.getCpf());
    }

    return false;
  }
}
