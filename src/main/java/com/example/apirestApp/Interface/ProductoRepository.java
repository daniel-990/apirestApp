package com.example.apirestApp.Interface;

import com.example.apirestApp.Model.Item;
import com.example.apirestApp.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}


