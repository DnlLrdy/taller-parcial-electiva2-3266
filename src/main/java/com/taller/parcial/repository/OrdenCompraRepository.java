package com.taller.parcial.repository;

import com.taller.parcial.model.OrdenCompra;
import com.taller.parcial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {
    List<OrdenCompra> findByUsuario(Usuario usuario);
}
