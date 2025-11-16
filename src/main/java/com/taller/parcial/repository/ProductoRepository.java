package com.taller.parcial.repository;

import com.taller.parcial.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByStockLessThan(int stock);
}
