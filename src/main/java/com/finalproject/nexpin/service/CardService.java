package com.finalproject.nexpin.service;

import com.finalproject.nexpin.entity.Card;
import com.finalproject.nexpin.exception.UserNotFoundException;

import java.util.List;

public interface CardService {
    List<Card> getAllActiveCardsOfUser(long userId) throws UserNotFoundException;
}
