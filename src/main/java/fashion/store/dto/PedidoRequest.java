package fashion.store.dto;

public record PedidoRequest(
        Long clienteId,
        Long produtoId,
        Integer quantidade
) {
}
