package com.user.user_entry.repositories;

import java.util.List;

import com.user.user_entry.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT * FROM items WHERE user_id = ?1 ORDER BY STR_TO_DATE(date, '%M %d, %Y') ASC", nativeQuery = true)
    List<Item> findAllByUserId(Integer id);

}