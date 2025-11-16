package com.taller.parcial.controller;

import com.taller.parcial.service.JwtUserService;
import com.taller.parcial.dto.request.CarritoCompraRequest;
import com.taller.parcial.dto.response.CarritoCompraResponse;
import com.taller.parcial.model.Usuario;
import com.taller.parcial.service.CarritoCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
@RequiredArgsConstructor
public class PrivateController {

    private final CarritoCompraService carritoCompraService;
    private final JwtUserService jwtUserService;

    @PostMapping("/crear-carrito-compra")
    public CarritoCompraResponse crearCarritoCompra(@RequestBody CarritoCompraRequest request) {
        Usuario usuario = jwtUserService.getUsuarioLogueado();
        return carritoCompraService.crearCarritoCompra(usuario, request);
    }

    @GetMapping("/listar-carritos-compra")
    public List<CarritoCompraResponse> listarCarritosCompra() {
        Usuario usuario = jwtUserService.getUsuarioLogueado();
        return carritoCompraService.listarCarritosCompraUsuario(usuario);
    }

}
