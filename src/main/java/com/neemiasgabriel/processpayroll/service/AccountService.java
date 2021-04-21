package com.neemiasgabriel.processpayroll.service;

import com.neemiasgabriel.processpayroll.model.Account;

public interface AccountService {

  void createAccount(Account account);
  void deleteAccountById(Long id);
  void updateAccount(Account account);
}
