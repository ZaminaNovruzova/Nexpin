package com.finalproject.nexpin.controller;

import com.finalproject.nexpin.entity.Accounts;
import com.finalproject.nexpin.entity.Card;
import com.finalproject.nexpin.entity.User;
import com.finalproject.nexpin.exception.UserNotFoundException;
import com.finalproject.nexpin.service.CardService;
import com.finalproject.nexpin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserAdminController {
    private final UserService userService;
    private final CardService cardService;

    @PostMapping("/user")
    public User createUser( @RequestBody User user) {
        System.out.println(user);
     User user1=userService.createUser(user);
        return user1;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/active_accounts")
    public List<Accounts> getActiveAccountsByUserId(Long id) throws UserNotFoundException {
        return userService.getActiveAccountsByUserId(id);
    }

    @GetMapping("/active_cards")
    public List<Card> getActiveCardsByUserId(@PathVariable long userId) throws UserNotFoundException {
        return cardService.getAllActiveCardsOfUser(userId);
    }

    //Seçilmiş istifadəçinin məlumatlarının alınması getUserByid (ADMİN, USER)
    //Seçilmiş istifadəçinin aktiv shesab məlumatlarının alınması  getAllAccountByUserİd (ADMİN, USER)
    //----------------------------------------------------------------------------------------
    //card
    //İstifadəçinin  bütün aktiv  kartlarının alınması (ADMİN, USER)
    //--------------------------------------------------------------------------------------------


}