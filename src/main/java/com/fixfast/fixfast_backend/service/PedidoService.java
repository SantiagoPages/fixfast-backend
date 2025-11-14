package com.fixfast.fixfast_backend.service;

import com.fixfast.fixfast_backend.dto.PedidoRequest;
import com.fixfast.fixfast_backend.model.DetallePedido;
import com.fixfast.fixfast_backend.model.Pedido;
import com.fixfast.fixfast_backend.model.Product;
import com.fixfast.fixfast_backend.repository.PedidoRepository;
import com.fixfast.fixfast_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final ProductRepository productRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoService(ProductRepository productRepository, PedidoRepository pedidoRepository) {
        this.productRepository = productRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido crearPedido(PedidoRequest pedidoRequest) {

        List<DetallePedido> detalles = new ArrayList<>();
        double total = 0;

        // Recorremos cada item enviado en el JSON
        for (PedidoRequest.ItemPedido item : pedidoRequest.getItems()) {

            // Buscar el product en la base
            Product product = productRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Product no encontrado: " + item.getProductoId()));
// Validar stock
            if (product.getStockActual() < item.getCantidad()) {
                throw new RuntimeException("No hay stock suficiente para el product: " + product.getNombre());
            }

            // Crear el detalle del pedido
            DetallePedido detalle = new DetallePedido();
            detalle.setProduct(product);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(product.getPrecioUnitario());

            // Guardar el detalle en lista temporal
            detalles.add(detalle);

            // Acumular total
            total += product.getPrecioUnitario() * item.getCantidad();

            // Descontar stock del product
            product.setStockActual(product.getStockActual() - item.getCantidad());
            productRepository.save(product);
        }

        // Crear el pedido final
        Pedido pedido = new Pedido();
        pedido.setNombreComprador(pedidoRequest.getNombreComprador());
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotalFinal(total);
        pedido.setDetalles(detalles);
// Conectar detalles con el pedido
        for (DetallePedido detalle : detalles) {
            detalle.setPedido(pedido);
        }

        // Guardar pedido y detalles en cascada
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}