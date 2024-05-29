package com.example.apirestApp.Service;

import com.example.apirestApp.Model.Producto;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final JdbcTemplate jdbcTemplate;

    public ProductoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Producto> getAllProductos() {
        return jdbcTemplate.query("SELECT * FROM productos", (rs, rowNum) ->
                new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio")));
    }

    public Producto getProductoById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM productos WHERE id = ?", new Object[]{id}, (rs, rowNum) ->
                new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio")));
    }

    @Transactional
    public void addProducto(Producto producto) {
        jdbcTemplate.update("INSERT INTO productos (nombre, precio) VALUES (?, ?)", producto.getNombre(), producto.getPrecio());
    }

    @Transactional
    public void updateProducto(Producto producto) {
        jdbcTemplate.update("UPDATE productos SET nombre = ?, precio = ? WHERE id = ?", producto.getNombre(), producto.getPrecio(), producto.getId());
    }

    @Transactional
    public void deleteProducto(Long id) {
        jdbcTemplate.update("DELETE FROM productos WHERE id = ?", id);
    }
}

