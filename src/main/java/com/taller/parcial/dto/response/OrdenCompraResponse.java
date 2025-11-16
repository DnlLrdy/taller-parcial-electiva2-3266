package com.taller.parcial.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrdenCompraResponse {
    private int id;
    private List<ProductoOrdenCompraResponse> productos;
    private double subtotal;
    private double impuestos;
    private double envio;
    private double total;
}
