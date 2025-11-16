package com.taller.parcial.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JwtResponse {
    private String token;
}