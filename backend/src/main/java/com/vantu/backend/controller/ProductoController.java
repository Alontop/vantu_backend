package com.vantu.backend.controller;
import com.vantu.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vantu.backend.model.Producto;

import java.util.List;



@RestController
@RequestMapping("/api/productos")

public class ProductoController {
    private final ProductoRepository productoRepository;
    @Autowired
    private ProductoRepository repository;

    ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return repository.findAll();
    }
}