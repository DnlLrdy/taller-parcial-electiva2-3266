package com.taller.parcial.controller;

import com.taller.parcial.dto.request.ComentarioRequest;
import com.taller.parcial.dto.request.ProductoRequest;
import com.taller.parcial.dto.response.ComentarioResponse;
import com.taller.parcial.dto.response.ProductoResponse;
import com.taller.parcial.model.Comentario;
import com.taller.parcial.model.Producto;
import com.taller.parcial.repository.ComentariosRepository;
import com.taller.parcial.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class PublicController {

    private final ProductoRepository productoRepository;
    private final ComentariosRepository comentariosRepository;

    @PostMapping("/productos")
    public List<ProductoResponse> listarProductos(@RequestBody ProductoRequest request) {
        List<Producto> productos = productoRepository.findByStockLessThan(request.getStockMax());
        return productos.stream()
                .map(p -> ProductoResponse.builder()
                        .id(p.getId())
                        .nombre(p.getNombre())
                        .descripcion(p.getDescripcion())
                        .precio(p.getPrecio())
                        .stock(p.getStock())
                        .categoria(p.getCategoria().getNombre())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/comentarios")
    public List<ComentarioResponse> listarComentarios(@RequestBody ComentarioRequest request) {
        LocalDateTime fechaDesde = request.getFechaDesde().atStartOfDay();
        List<Comentario> comentarios = comentariosRepository.findByFechaAfter(fechaDesde);
        return comentarios.stream()
                .map(c -> ComentarioResponse.builder()
                        .id(c.getId())
                        .producto(c.getProducto().getNombre())
                        .usuario(c.getUsuario().getNombre())
                        .comentario(c.getComentario())
                        .fecha(c.getFecha())
                        .build())
                .collect(Collectors.toList());
    }

}
