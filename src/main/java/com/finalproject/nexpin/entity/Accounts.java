package com.finalproject.nexpin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    // Müştərinin ən azı 2 hesabı olmalıdır ( cashback, debit)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    @ManyToOne
    private User user;
    String accountNumber;

    String currency;
    double balance;


    @ManyToOne
    private AccountType accountType;
    boolean isActive;



}
