package com.taller.parcial.dto.request;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ComentarioRequest {
    private LocalDate fechaDesde;
}
