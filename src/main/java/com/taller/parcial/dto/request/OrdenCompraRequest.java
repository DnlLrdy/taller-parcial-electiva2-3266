package com.taller.parcial.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrdenCompraRequest {
    private int carritoId;
    private double envio;
}
