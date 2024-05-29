package com.example.apirestApp.Controller;

import com.example.apirestApp.Model.Producto;
import com.example.apirestApp.Interface.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    private final JdbcTemplate jdbcTemplate;

    public ProductoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        //return productoRepository.findAll();
        return jdbcTemplate.query("SELECT id, nombre, precio FROM producto", (rs, rowNum) ->
                new Producto(rs.getLong("id"), rs.getString("nombre"),rs.getBigDecimal("precio")));

    }

    @GetMapping("/productos/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElseThrow();
    }

    @PostMapping("/productos")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/productos/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/productos/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
