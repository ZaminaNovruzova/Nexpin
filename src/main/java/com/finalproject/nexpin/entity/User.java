package com.finalproject.nexpin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String userName;

    String email;

    String password;
    String fullName;
    boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "role_id",referencedColumnName = "id") )
    List<Role> roles=new ArrayList<>();
}
