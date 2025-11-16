package com.taller.parcial.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductoCarritoCompraResponse {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;
    private int stock;
}
