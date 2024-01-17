package com.finalproject.nexpin.manager;

import com.finalproject.nexpin.entity.Accounts;
import com.finalproject.nexpin.entity.Card;
import com.finalproject.nexpin.entity.User;
import com.finalproject.nexpin.exception.AccountNotFoundException;
import com.finalproject.nexpin.exception.UserNotFoundException;
import com.finalproject.nexpin.repository.AccountRepository;
import com.finalproject.nexpin.repository.CardRepository;
import com.finalproject.nexpin.repository.UserRepository;
import com.finalproject.nexpin.service.CardService;
import com.finalproject.nexpin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService, CardService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Override
    public User createUser(User user) {
     return userRepository.save(user);
    }

    @Override
    public void addAccountToUser(Long userId, Accounts account) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            account.setUser(user);
            accountRepository.save(account);

        } else
            throw new Exception("The account with ID " + account.getAccountId() + " already belongs to another user");
    }

    @Override
    public void disableUserAccount(Long userId, Long accountId) throws AccountNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Accounts account = accountRepository.findById(accountId).orElse(null);
            if (account != null) {
                account.setActive(false);
                accountRepository.save(account);

            } else throw new AccountNotFoundException("Account " + accountId + " is not found");
        }
    }

    @Override
    public void disableUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActive(false);
        } else throw new UserNotFoundException("User " + userId + " is not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<Accounts> getActiveAccountsByUserId(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return accountRepository.findAccountByUserId(userId);
        } else throw new UserNotFoundException("User " + userId + " is not found");
    }

    @Override
    public List<Card> getAllActiveCardsOfUser(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return cardRepository.findCardByUserId(userId);

        } else throw new UserNotFoundException("User " + userId + " is not found");

    }
}