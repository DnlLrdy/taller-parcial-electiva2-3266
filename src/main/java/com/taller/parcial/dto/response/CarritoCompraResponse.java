package com.taller.parcial.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarritoCompraResponse {
    private int id;
    private List<ProductoCarritoCompraResponse> productos;
    private double subtotal;
    private double impuestos;
}
