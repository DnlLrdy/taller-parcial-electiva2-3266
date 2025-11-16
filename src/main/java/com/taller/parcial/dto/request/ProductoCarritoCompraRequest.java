package com.taller.parcial.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductoCarritoCompraRequest {
    private int productoId;
    private int cantidad;
}
