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
    String respuesta = "";
    public ProductoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        //return productoRepository.findAll();
        return jdbcTemplate.query("SELECT * FROM producto", (rs, rowNum) ->
                new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio")));
    }

    @GetMapping("/productos/{id}")
    public List<Producto> getProductoById(@PathVariable Long id) {
        //return productoRepository.findById(id).orElseThrow();
        return jdbcTemplate.query("SELECT * FROM producto where id = '"+id+"'", (rs, rowNum) ->
                new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getBigDecimal("precio")));
    }

    @PostMapping("/productos")
    public String createProducto(@RequestBody Producto producto) {
        //return productoRepository.save(producto);
        int consulta = jdbcTemplate.update("INSERT INTO producto (nombre, precio) VALUES (?, ?)", producto.getNombre(), producto.getPrecio());
        if(consulta != 1){
            respuesta = "NO creo el item de manera exitosa [ "+ false +" ]";
            return respuesta;
        }else{
            respuesta = "creacion de item de manera exitosa [ "+ true +" ]";
            return respuesta;
        }
    }

    @PutMapping("/productos/{id}")
    public String updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        //return productoRepository.save(producto);
        int consulta = jdbcTemplate.update("UPDATE producto SET nombre = ?, precio = ? WHERE id = ?", producto.getNombre(), producto.getPrecio(), producto.getId());
        if(consulta != 1){
            respuesta = "NO se actualizo el item [ "+ false +" ]";
            return respuesta;
        }else{
            respuesta = "se actualizo el item de manera exitosa [ "+ true +" ]";
            return respuesta;
        }
    }

    @DeleteMapping("/productos/{id}")
    public String deleteProducto(@PathVariable Long id) {
        //productoRepository.deleteById(id);
        int consulta = jdbcTemplate.update("DELETE FROM producto WHERE id = ?", id);
        if(consulta != 1){
            respuesta = "NO se elimino el item [ "+ false +" ]";
            return respuesta;
        }else{
            respuesta = "se elimino el item de manera exitosa [ "+ true +" ]";
            return respuesta;
        }
    }
}
