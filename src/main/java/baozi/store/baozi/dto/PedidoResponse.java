package baozi.store.baozi.dto;

import java.math.BigDecimal;

public record PedidoResponse(
        String nomeCliente,
        String nomeProduto,
        BigDecimal precoProduto,
        Integer quantidade
) {
}
