package com.finalproject.nexpin.repository;

import com.finalproject.nexpin.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("select c from Cards c where c.user.id=:userId and c.isActive=true ")
    List<Card> findCardByUserId(@Param("userId") long userId);

}
