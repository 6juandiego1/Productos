package com.aws.cognito.productos.controller;

import com.aws.cognito.productos.dto.ProductoDTO;
import com.aws.cognito.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    //@PreAuthorize("hasAuthority('ROLE_Reader') or hasAuthority('ROLE_writer')")
    public List<ProductoDTO> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_Reader') or hasAuthority('ROLE_writer')")
    public ProductoDTO obtener(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ROLE_writer')")
    public ProductoDTO crear(@RequestBody ProductoDTO p) {
        return service.save(p);
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('ROLE_writer')")
    public ProductoDTO actualizar(@RequestBody ProductoDTO p) {
        return service.update(p);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_writer')")
    public void eliminar(@PathVariable String id) {
        service.delete(id);
    }
}
