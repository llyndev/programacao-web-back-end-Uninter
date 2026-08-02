package baozi.store.baozi.dto;

public record PedidoRequest(
        Long clienteId,
        Long produtoId,
        Integer quantidade
) {
}
