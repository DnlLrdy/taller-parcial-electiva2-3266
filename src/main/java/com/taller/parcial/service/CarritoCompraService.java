package com.taller.parcial.service;

import com.taller.parcial.dto.request.CarritoCompraRequest;
import com.taller.parcial.dto.request.ProductoCarritoCompraRequest;
import com.taller.parcial.dto.response.CarritoCompraResponse;
import com.taller.parcial.dto.response.ProductoCarritoCompraResponse;
import com.taller.parcial.model.CarritoCompra;
import com.taller.parcial.model.Producto;
import com.taller.parcial.model.Usuario;
import com.taller.parcial.repository.CarritoCompraRepository;
import com.taller.parcial.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarritoCompraService {

    private final CarritoCompraRepository carritoCompraRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public CarritoCompraResponse crearCarritoCompra(Usuario usuario, CarritoCompraRequest request) {
        List<Integer> productoIds = request.getProductos().stream()
                .map(ProductoCarritoCompraRequest::getProductoId)
                .collect(Collectors.toList());

        List<Producto> productosDB = productoRepository.findAllById(productoIds);

        Map<Integer, Producto> productoMap = productosDB.stream()
                .collect(Collectors.toMap(Producto::getId, p -> p));

        List<Producto> productosCarrito = new ArrayList<>();

        double subtotal = 0;

        for (ProductoCarritoCompraRequest pReq : request.getProductos()) {
            Producto producto = productoMap.get(pReq.getProductoId());
            if (producto == null) {
                throw new RuntimeException("Producto con ID " + pReq.getProductoId() + " no encontrado");
            }
            if (producto.getStock() < pReq.getCantidad()) {
                throw new RuntimeException("Producto " + producto.getNombre() + " sin stock suficiente");
            }

            producto.setStock(producto.getStock() - pReq.getCantidad());
            for (int i = 0; i < pReq.getCantidad(); i++) {
                productosCarrito.add(producto);
            }

            subtotal += producto.getPrecio() * pReq.getCantidad();
        }

        productoRepository.saveAll(productosDB);

        double impuestos = subtotal * 0.19;

        CarritoCompra carrito = CarritoCompra.builder()
                .usuario(usuario)
                .productos(productosCarrito)
                .subtotal(subtotal)
                .impuestos(impuestos)
                .build();

        carritoCompraRepository.save(carrito);

        return mapCarritoCompraResponse(carrito);
    }

    public List<CarritoCompraResponse> listarCarritosCompraUsuario(Usuario usuario) {
        List<CarritoCompra> carritos = carritoCompraRepository.findByUsuario(usuario);

        return carritos.stream()
                .map(this::mapCarritoCompraResponse)
                .collect(Collectors.toList());
    }

    private CarritoCompraResponse mapCarritoCompraResponse(CarritoCompra carrito) {
        Map<Integer, Long> cantidadMap = carrito.getProductos().stream()
                .collect(Collectors.groupingBy(Producto::getId, Collectors.counting()));

        List<ProductoCarritoCompraResponse> productos = carrito.getProductos().stream()
                .distinct()
                .map(p -> ProductoCarritoCompraResponse.builder()
                        .id(p.getId())
                        .nombre(p.getNombre())
                        .precio(p.getPrecio())
                        .cantidad(cantidadMap.get(p.getId()).intValue())
                        .stock(p.getStock())
                        .build())
                .collect(Collectors.toList());

        return CarritoCompraResponse.builder()
                .id(carrito.getId())
                .productos(productos)
                .subtotal(carrito.getSubtotal())
                .impuestos(carrito.getImpuestos())
                .build();
    }

}
