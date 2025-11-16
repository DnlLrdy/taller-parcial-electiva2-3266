package com.taller.parcial.service;

import com.taller.parcial.dto.request.OrdenCompraRequest;
import com.taller.parcial.dto.response.OrdenCompraResponse;
import com.taller.parcial.dto.response.ProductoOrdenCompraResponse;
import com.taller.parcial.model.CarritoCompra;
import com.taller.parcial.model.OrdenCompra;
import com.taller.parcial.model.Producto;
import com.taller.parcial.model.Usuario;
import com.taller.parcial.repository.CarritoCompraRepository;
import com.taller.parcial.repository.OrdenCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final CarritoCompraRepository carritoCompraRepository;

    public OrdenCompraResponse crearOrdenCompra(Usuario usuario, OrdenCompraRequest request) {
        CarritoCompra carrito = carritoCompraRepository.findById(request.getCarritoId())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getUsuario().getId() != usuario.getId()) {
            throw new RuntimeException("El carrito no pertenece al usuario logueado");
        }

        double subtotal = carrito.getSubtotal();
        double impuestos = carrito.getImpuestos();
        double envio = request.getEnvio();
        double total = subtotal + impuestos + envio;

        OrdenCompra orden = OrdenCompra.builder()
                .usuario(usuario)
                .productos(new ArrayList<>(carrito.getProductos()))
                .subtotal(subtotal)
                .impuestos(impuestos)
                .envio(envio)
                .total(total)
                .build();

        ordenCompraRepository.save(orden);

        return mapOrdenCompraResponse(orden);
    }

    public List<OrdenCompraResponse> listarOrdenesCompra(Usuario usuario) {
        List<OrdenCompra> ordenes = ordenCompraRepository.findByUsuario(usuario);
        return ordenes.stream()
                .map(this::mapOrdenCompraResponse)
                .collect(Collectors.toList());
    }

    private OrdenCompraResponse mapOrdenCompraResponse(OrdenCompra orden) {
        Map<Integer, Long> cantidadMap = orden.getProductos().stream()
                .collect(Collectors.groupingBy(Producto::getId, Collectors.counting()));

        List<ProductoOrdenCompraResponse> productos = orden.getProductos().stream()
                .distinct()
                .map(p -> ProductoOrdenCompraResponse.builder()
                        .id(p.getId())
                        .nombre(p.getNombre())
                        .precio(p.getPrecio())
                        .cantidad(cantidadMap.get(p.getId()).intValue())
                        .build())
                .collect(Collectors.toList());

        return OrdenCompraResponse.builder()
                .id(orden.getId())
                .productos(productos)
                .subtotal(orden.getSubtotal())
                .impuestos(orden.getImpuestos())
                .envio(orden.getEnvio())
                .total(orden.getTotal())
                .build();
    }

}
