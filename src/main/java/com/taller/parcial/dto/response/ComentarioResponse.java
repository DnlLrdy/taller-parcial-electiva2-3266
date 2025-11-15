package com.taller.parcial.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ComentarioResponse {
    private int id;
    private String producto;
    private String usuario;
    private String comentario;
    private LocalDateTime fecha;
}
