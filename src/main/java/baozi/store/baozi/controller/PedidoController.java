package baozi.store.baozi.controller;

import baozi.store.baozi.dto.CancelarPedidoRequest;
import baozi.store.baozi.dto.PedidoRequest;
import baozi.store.baozi.dto.PedidoResponse;
import baozi.store.baozi.model.Pedido;
import baozi.store.baozi.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> registrarPedido(@RequestBody PedidoRequest request) {
        return ResponseEntity.ok(pedidoService.registrarPedido(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.ok().build();
    }

}
