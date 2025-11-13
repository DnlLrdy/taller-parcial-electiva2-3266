package com.taller.parcial.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 300)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

}
