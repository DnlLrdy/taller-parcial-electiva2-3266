package com.taller.parcial.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginRequest {
    private String correoElectronico;
    private String contraseña;
}
