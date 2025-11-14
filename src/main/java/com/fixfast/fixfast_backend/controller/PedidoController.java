package com.fixfast.fixfast_backend.controller;

import com.fixfast.fixfast_backend.dto.PedidoRequest;
import com.fixfast.fixfast_backend.model.Pedido;
import com.fixfast.fixfast_backend.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Crear un pedido
    @PostMapping
    public Pedido crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.crearPedido(pedidoRequest);
    }

    // Listar pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }
}
