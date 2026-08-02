package baozi.store.baozi.controller;

import baozi.store.baozi.dto.PedidoRequest;
import baozi.store.baozi.dto.PedidoResponse;
import baozi.store.baozi.model.Pedido;
import baozi.store.baozi.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> registrarPedido(@RequestBody PedidoRequest request) {
        Pedido pedido = pedidoService.registrarPedido(request);

        PedidoResponse response = new PedidoResponse(
                pedido.getCliente().getNome(),
                pedido.getProduto().getNome(),
                pedido.getProduto().getPreco(),
                pedido.getQuantidade()
        );

        return ResponseEntity.ok(response);
    }

}
