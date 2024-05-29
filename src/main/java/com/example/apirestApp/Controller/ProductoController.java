package com.example.apirestApp.Controller;

import com.example.apirestApp.Model.Producto;
import com.example.apirestApp.Interface.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
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
