package com.finalproject.nexpin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Month;
import java.time.Year;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name="Cards")
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    //Card(id, card_name, pan, expire_month, expire_year,cvc_number, user_id, account_id, is_active)
    //Pan 16 rəqəmlidir (1234 **** **** 4567) formada saxlanmalıdır
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String cardName;
    long pan; //kartin ustundeki 16 reqem //String kimi saxlasan daha az yer tutur
    Month expireMonth;
    Year expireYear;
    int cvcNumber; //gizlidir
    @ManyToOne
    private User user;
    long accountId;
    boolean isActive;

}
