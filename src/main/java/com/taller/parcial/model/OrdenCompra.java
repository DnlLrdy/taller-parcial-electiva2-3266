package com.taller.parcial.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ordenes_de_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "orden_de_productos",
            joinColumns = @JoinColumn(name = "id_orden_de_compra", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_producto", nullable = false)
    )
    private List<Producto> productos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private double subtotal;

    @Column(nullable = false)
    private double impuestos;

    @Column(nullable = false)
    private double envio;

    @Column(nullable = false)
    private double total;

}
