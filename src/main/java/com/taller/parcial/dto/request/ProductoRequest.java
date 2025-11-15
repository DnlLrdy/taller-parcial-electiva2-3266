package com.taller.parcial.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductoRequest {
    private int stockMax;
}