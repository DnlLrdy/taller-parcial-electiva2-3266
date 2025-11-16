package com.taller.parcial.repository;

import com.taller.parcial.model.CarritoCompra;
import com.taller.parcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Integer> {
    List<CarritoCompra> findByUsuario(Usuario usuario);
}