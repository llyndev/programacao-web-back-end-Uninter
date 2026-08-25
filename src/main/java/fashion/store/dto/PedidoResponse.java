package fashion.store.dto;

import fashion.store.model.Pedido;

import java.math.BigDecimal;

public record PedidoResponse(
        String nomeCliente,
        String nomeProduto,
        BigDecimal precoProduto,
        Integer quantidade,

        boolean status
) {

    public PedidoResponse(Pedido pedido){
        this(pedido.getCliente().getNome(), pedido.getProduto().getNome(), pedido.getProduto().getPreco(), pedido.getQuantidade(), pedido.getStatus());
    }

}
