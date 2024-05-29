package com.example.apirestApp.Interface;

import com.example.apirestApp.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}