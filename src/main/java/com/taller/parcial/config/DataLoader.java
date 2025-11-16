package com.taller.parcial.config;

import com.taller.parcial.model.enums.MetodoPago;
import com.taller.parcial.model.Usuario;
import com.taller.parcial.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (usuarioRepository.count() == 0) {

            usuarioRepository.save(Usuario.builder()
                    .nombre("Juan Pérez")
                    .correoElectronico("juan.perez@email.com")
                    .contraseña(passwordEncoder.encode("Qwerty123"))
                    .direccion("Carrera 45 #10-20")
                    .metodoPago(MetodoPago.TARJETA_CREDITO)
                    .build());

            usuarioRepository.save(Usuario.builder()
                    .nombre("Ana Gómez")
                    .correoElectronico("ana.gomez@email.com")
                    .contraseña(passwordEncoder.encode("Pass456"))
                    .direccion("Calle 21 #35-50")
                    .metodoPago(MetodoPago.PAYPAL)
                    .build());

            usuarioRepository.save(Usuario.builder()
                    .nombre("Carlos Ruiz")
                    .correoElectronico("carlos.ruiz@email.com")
                    .contraseña(passwordEncoder.encode("Segura789"))
                    .direccion("Avenida Principal #100")
                    .metodoPago(MetodoPago.TRANSFERENCIA_BANCARIA)
                    .build());

            usuarioRepository.save(Usuario.builder()
                    .nombre("Sofía Martínez")
                    .correoElectronico("sofia.martinez@email.com")
                    .contraseña(passwordEncoder.encode("Clave987"))
                    .direccion("Calle 8 #20-30")
                    .metodoPago(MetodoPago.EFECTIVO)
                    .build());

            usuarioRepository.save(Usuario.builder()
                    .nombre("Diego Fernández")
                    .correoElectronico("diego.fernandez@email.com")
                    .contraseña(passwordEncoder.encode("Contra654"))
                    .direccion("Carrera 77 #40-60")
                    .metodoPago(MetodoPago.TARJETA_DEBITO)
                    .build());

        }

    }

}
