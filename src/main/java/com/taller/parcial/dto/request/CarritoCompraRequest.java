package com.taller.parcial.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CarritoCompraRequest {
    private List<ProductoCarritoCompraRequest> productos;
}
