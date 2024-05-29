package com.example.apirestApp.Controller;

import com.example.apirestApp.Interface.ItemRepository;
import com.example.apirestApp.Model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable int id){
        return itemRepository.findById(id).orElseThrow();
    }

    @PostMapping("/items")
    public Item crearItem(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @PutMapping("/item/{id}")
    public Item actualizarItem(@PathVariable int id, @RequestBody Item item){
        item.setId(id);
        return itemRepository.save(item);
    }

    @DeleteMapping("/items/{id}")
    public void borrarItem(@PathVariable int id){
        itemRepository.deleteById(id);
    }
}
