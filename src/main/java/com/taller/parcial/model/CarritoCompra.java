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
@Table(name = "carritos_de_compras")
public class CarritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "carrito_de_productos",
            joinColumns = @JoinColumn(name = "id_carrito_de_compra", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_producto", nullable = false)
    )
    private List<Producto> productos;

    @Column(nullable = false)
    private double subtotal;

    @Column(nullable = false)
    private double impuestos;

}
