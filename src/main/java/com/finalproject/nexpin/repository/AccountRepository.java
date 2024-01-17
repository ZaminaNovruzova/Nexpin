package com.finalproject.nexpin.repository;

import com.finalproject.nexpin.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Accounts, Long> {
    @Query("select acc from accounts acc where acc.user.id=:userId and acc.isActive=true ")
    List<Accounts> findAccountByUserId(@Param("userId") Long userId);

}
