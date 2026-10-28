package com.aws.cognito.productos.service;

import com.aws.cognito.productos.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private List<ProductoDTO> productos = new ArrayList<>();

    public List<ProductoDTO> findAll() {
        return productos;
    }

    public ProductoDTO findById(String id) {
        ProductoDTO p = new ProductoDTO();
        p.setId(id);

        int i = productos.indexOf(p);
        return productos.get(i);
    }

    public ProductoDTO save(ProductoDTO producto) {
        productos.add(producto);
        return producto;
    }

    public ProductoDTO update(ProductoDTO producto) {
        int i = productos.indexOf(producto);
        productos.set(i, producto);
        return producto;
    }

    public void delete(String id) {
        ProductoDTO p = findById(id);
        productos.remove(p);
    }
}
