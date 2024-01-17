package com.finalproject.nexpin.controller;

import com.finalproject.nexpin.entity.Accounts;
import com.finalproject.nexpin.entity.User;
import com.finalproject.nexpin.exception.AccountNotFoundException;
import com.finalproject.nexpin.exception.UserNotFoundException;
import com.finalproject.nexpin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    @GetMapping("/test")
    public String test(){
        return "SUCCESS";
    }

    @PostMapping("/add_account")
    public void addAccToUser(@PathVariable long Id, @RequestBody Accounts account) throws Exception {
        userService.addAccountToUser(Id, account);
    }

    @PostMapping(value = "/deactivate_account")
    public void deactivateUserAccount(@PathVariable long Id, @RequestBody Accounts account) throws AccountNotFoundException {
        userService.disableUserAccount(Id, account.getAccountId());
    }

    @PostMapping(value = "/deactivate_user")
    public void deactivateUser(@PathVariable long Id) throws UserNotFoundException {
        userService.disableUser(Id);

    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    //istifadəçiyə hesab əlavə edə bilməlidir (ADMİN) add save
    //istifadəçi hesabını  deaktiv etmək imkanı olmalıdır(ADMİN)  deactive isactive false
    //istifadəçini deaktiv edə bilməlidir (ADMİN). user isactive false
    //Bütün istifadəçilərin məlumatlarının alınması  (ADMİN) find all
    //transaction
    //Bütün tranzaksiyaların hesabatı (ADMİN)

}
