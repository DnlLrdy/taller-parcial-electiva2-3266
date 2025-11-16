package com.taller.parcial.repository;

import com.taller.parcial.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByFechaAfter(LocalDateTime fecha);
}
