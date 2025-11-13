package com.taller.parcial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taller.parcial.model.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, unique = true, length = 120)
    private String correoElectronico;

    @JsonIgnore
    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false, length = 30)
    private MetodoPago metodoPago;

}
