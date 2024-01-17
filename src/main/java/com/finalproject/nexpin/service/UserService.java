package com.finalproject.nexpin.service;

import com.finalproject.nexpin.entity.Accounts;
import com.finalproject.nexpin.entity.Card;
import com.finalproject.nexpin.entity.User;
import com.finalproject.nexpin.exception.AccountNotFoundException;
import com.finalproject.nexpin.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
  User createUser(User user);
  void addAccountToUser(Long userId, Accounts account) throws Exception;
  void disableUserAccount(Long userId, Long accountId) throws AccountNotFoundException;

  void disableUser(Long userId) throws UserNotFoundException;
  List<User> getAllUsers();
  User getUserById(Long id);
  List<Accounts> getActiveAccountsByUserId(Long userId) throws UserNotFoundException;



//  void register(RegisterRequest registerRequest);





}
